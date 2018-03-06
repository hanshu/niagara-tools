package com.tridiumap.niagaraProxyEdge.util;

public class Constants {
    public static final String PREFIX = "v_";

    public static final String ORIGINAL_POINT_REGEX = "(.+)";
    public static final String WRAPPER_POINT = "v_$1_v";

    public static final String WRAPPER_POINT_REGEX = "^v_(.*)_v$";
    public static final String UNWRAPPER_POINT = "$1";

}
