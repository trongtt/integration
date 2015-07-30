/*
 * Copyright (C) 2003-2014 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.wcm.ext.component.activity;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.commons.lang.StringUtils;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.social.core.manager.ActivityManager;
import org.exoplatform.wcm.ext.component.document.service.ShareDocumentService;
import org.exoplatform.services.cms.link.LinkManager;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.core.ManageableRepository;
import org.exoplatform.services.jcr.ext.common.SessionProvider;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.wcm.utils.WCMCoreUtils;
import org.exoplatform.social.core.activity.model.ExoSocialActivity;
import org.exoplatform.social.webui.activity.BaseUIActivity;
import org.exoplatform.social.webui.activity.BaseUIActivityBuilder;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Nov 25, 2014  
 */
public class UISharedContentBuilder extends BaseUIActivityBuilder{
  private static final Log LOG = ExoLogger.getLogger(UISharedContentBuilder.class);
  @Override
  protected void extendUIActivity(BaseUIActivity uiActivity, ExoSocialActivity activity) {
    SharedContentUIActivity contentActivity = (SharedContentUIActivity) uiActivity;
    String nodePath = "";
    String workspaceName = "";
    //set data into the UI component of activity
    if (activity.getTemplateParams() != null) {
      contentActivity.setUIActivityData(activity.getTemplateParams());
      nodePath = activity.getTemplateParams().get(ContentUIActivity.NODE_PATH);
      workspaceName = activity.getTemplateParams().get(ContentUIActivity.WORKSPACE);
    }
    //get node data
    RepositoryService repositoryService = WCMCoreUtils.getService(RepositoryService.class);
    ManageableRepository manageRepo = null;
    Node contentNode = null;
    try {
      manageRepo = repositoryService.getCurrentRepository();
      if(StringUtils.isEmpty(workspaceName)) workspaceName = manageRepo.getConfiguration().getDefaultWorkspaceName();
      SessionProvider sessionProvider = WCMCoreUtils.getUserSessionProvider();
      LinkManager linkManager = WCMCoreUtils.getService(LinkManager.class);
      contentNode = linkManager.getTarget((Node) sessionProvider.getSession(workspaceName, manageRepo).getItem(nodePath));
      contentActivity.docPath = contentNode.getPath();
      contentActivity.workspace = workspaceName;
      contentActivity.repository = manageRepo.toString();
      contentActivity.setActivityTitle(activity.getTitle().replace("</br></br>", ""));
    } catch (RepositoryException re) {
      if(LOG.isErrorEnabled())
        LOG.error("Can not get the repository. ", re);
      ActivityManager activityManager = WCMCoreUtils.getService(ActivityManager.class);
      activityManager.deleteActivity(activity);
    }
    contentActivity.setContentNode(contentNode);
  }

}
