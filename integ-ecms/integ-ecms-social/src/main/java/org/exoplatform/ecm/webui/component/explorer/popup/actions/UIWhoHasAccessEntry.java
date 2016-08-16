///*
// * Copyright (C) 2003-2014 eXo Platform SAS.
// *
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU Affero General Public License as published by
// * the Free Software Foundation, either version 3 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU Affero General Public License for more details.
// *
// * You should have received a copy of the GNU Affero General Public License
// * along with this program. If not, see <http://www.gnu.org/licenses/>.
// */
//package org.exoplatform.ecm.webui.component.explorer.popup.actions;
//
//
//import org.exoplatform.ecm.webui.component.explorer.versions.UILabelForm;
//import org.exoplatform.portal.webui.util.Util;
//import org.exoplatform.services.jcr.access.PermissionType;
//import org.exoplatform.services.log.ExoLogger;
//import org.exoplatform.services.log.Log;
//import org.exoplatform.services.resources.ResourceBundleService;
//import org.exoplatform.services.wcm.utils.WCMCoreUtils;
//import org.exoplatform.webui.config.annotation.ComponentConfig;
//import org.exoplatform.webui.config.annotation.EventConfig;
//import org.exoplatform.webui.core.UIComponent;
//import org.exoplatform.webui.core.UIContainer;
//import org.exoplatform.webui.core.lifecycle.UIContainerLifecycle;
//import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
//import org.exoplatform.webui.core.model.SelectItemOption;
//import org.exoplatform.webui.event.Event;
//import org.exoplatform.webui.event.EventListener;
//import org.exoplatform.webui.form.UIForm;
//import org.exoplatform.webui.form.UIFormInputSet;
//import org.exoplatform.webui.form.UIFormSelectBox;
//import org.exoplatform.webui.form.UIFormTableInputSet;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.ResourceBundle;
//
///**
// * Created by The eXo Platform SAS
// * Author : Walid Khessairi
// *          wkhessairi@exoplatform.com
// * Aug 11, 2016
// */
//@ComponentConfig(
//        template =  "classpath:groovy/ecm/social-integration/share-document/UIWhoHasAccessEntry.gtmpl"
//)
//public class UIWhoHasAccessEntry extends UIContainer {
//
//    private static final Log    LOG                 = ExoLogger.getLogger(UIWhoHasAccess.class);
//
//
////    public static class PermissionRemoveActionListener extends EventListener<UIWhoHasAccess>{
////
////        @Override
////        public void execute(Event<UIWhoHasAccess> event) throws Exception {
////            UIWhoHasAccess uiform = event.getSource();
////            //uiform.spaces.remove(event.getRequestContext().getRequestParameter(OBJECTID).toString());
////            //if (event.getSource().getChild(UIFormTextAreaInput.class).getValue() == null) uiform.comment = "";
////            //else uiform.comment = event.getSource().getChild(UIFormTextAreaInput.class).getValue();
////            //UIShareDocumentSpaceMention uiShareDocumentSpaceMention = event.getSource().findFirstComponentOfType(UIShareDocumentSpaceMention.class);
////            //event.getRequestContext().addUIComponentToUpdateByAjax(uiShareDocumentSpaceMention);
//////            event.getRequestContext().getJavascriptManager()
//////                    .require("SHARED/share-content", "shareContent")
//////                    .addScripts("eXo.ecm.ShareContent.checkSelectedSpace('"+uiform.spaces+"');");
////        }
////    }
////
////    public static class PermissionChangeActionListener extends EventListener<UIWhoHasAccess>{
////
////        @Override
////        public void execute(Event<UIWhoHasAccess> event) throws Exception {
////            UIWhoHasAccess uiform = event.getSource();
////            //uiform.spaces.remove(event.getRequestContext().getRequestParameter(OBJECTID).toString());
////            //if (event.getSource().getChild(UIFormTextAreaInput.class).getValue() == null) uiform.comment = "";
////            //else uiform.comment = event.getSource().getChild(UIFormTextAreaInput.class).getValue();
////            //UIShareDocumentSpaceMention uiShareDocumentSpaceMention = event.getSource().findFirstComponentOfType(UIShareDocumentSpaceMention.class);
////            //event.getRequestContext().addUIComponentToUpdateByAjax(uiShareDocumentSpaceMention);
//////            event.getRequestContext().getJavascriptManager()
//////                    .require("SHARED/share-content", "shareContent")
//////                    .addScripts("eXo.ecm.ShareContent.checkSelectedSpace('"+uiform.spaces+"');");
////        }
////    }
//
//
//    private String identity;
//
//    private UIFormSelectBox permission;
//
//    public void UIWhoHasAccessEntry() {
//
//    }
//
//    public String getPermission(String identity) {
//        return permissions.get(identity);
//    }
//
//    public void setPermission(String identity, String permission) {
//        permissions.put(identity,permission);
//    }
//
//    public Map<String, String> getAllPermissions() {
//        return this.permissions;
//    }
//
//
//    public void getIdentityName(String identity) {
//        try {
//            addChild(createUIComponent(UIFormTableInputSet.class, null, null));
//            UIContainer uiContainer = getChild(UIContainer.class);
//            ResourceBundleService resourceBundleService = WCMCoreUtils.getService(ResourceBundleService.class);
//            ResourceBundle resourceBundle = resourceBundleService.getResourceBundle(SHARECONTENT_BUNDLE_LOCATION, Util.getPortalRequestContext().getLocale());
//            String canView = resourceBundle.getString(SHARE_OPTION_CANVEW);
//            String canModify = resourceBundle.getString(SHARE_OPTION_CANMODIFY);
//            List<SelectItemOption<String>> itemOptions = new ArrayList<SelectItemOption<String>>();
//            itemOptions.add(new SelectItemOption<String>(canView, SHARE_PERMISSION_VIEW));
//            itemOptions.add(new SelectItemOption<String>(canModify, SHARE_PERMISSION_MODIFY));
//            ArrayList<SelectItemOption<String>> permOption = new ArrayList<SelectItemOption<String>>();
//            uiContainer.addChild(new UIFormSelectBox("identityPermission", "identityPermission", permOption));
//            UIFormSelectBox uiFormSelectBox = uiContainer.getChild(UIFormSelectBox.class);
//            uiFormSelectBox.setOptions(itemOptions);
//            uiFormSelectBox.setValue(getPermission(identity));
//            uiContainer.renderChild("identityPermission");
//        } catch (Exception e) {
//            if (LOG.isErrorEnabled())
//                LOG.error(e.getMessage(), e);
//        }
//    }
//
//}
