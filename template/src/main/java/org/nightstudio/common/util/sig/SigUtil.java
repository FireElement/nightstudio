package org.nightstudio.common.util.sig;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.nightstudio.common.util.encrypt.EncryptUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SigUtil {
    protected static Logger logger = Logger.getLogger(SigUtil.class);

    /**
     * 根据appid、token、lol以及时间戳来生成签名
     * @param list
     * @return
     */
    public static String calSig(List<String> list) throws Throwable {
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str);
        }

        return EncryptUtil.SHA1(sb.toString());
    }

    /**
     * 验证签名: <br/>
     * 2.根据appid、token、lol以及时间戳计算一次签名;<br/>
     * 3.比较传过来的签名以及计算出的签名是否一致;
     * @param sign
     * @param list
     * @return
     */
    public static boolean isValidSig(String sign, List<String> list) throws Throwable {
        String calSign = calSig(list);
        if (StringUtils.equals(calSign, sign)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        List<String> srcList = new ArrayList<String>();
        srcList.add("13916158560");
        srcList.add("22222222");
        String result = null;
        try {
            result = SigUtil.calSig(srcList);
        } catch (Throwable e) {
            logger.warn("", e);
        }
        System.out.println(result);
    }

}
