package org.nightstudio.common.util.constant;

import org.nightstudio.common.util.properties.PropertiesUtil;

public interface ApplicationConstant {
    String ROOT_PATH = PropertiesUtil.getProperty(PropertiesConstant.ROOT_PATH);
    String ROOT_PACKAGE = PropertiesUtil.getProperty(PropertiesConstant.ROOT_PACKAGE);

    int PAGINATION_LINK_COUNT = 5;
    int PAGE_SIZE = 20;

    String CLIENT_DOMAIN = "*";
}
