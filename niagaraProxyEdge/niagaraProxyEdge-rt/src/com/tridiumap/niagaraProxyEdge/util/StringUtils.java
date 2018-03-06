package com.tridiumap.niagaraProxyEdge.util;

public class StringUtils {
    /**
     * Wrap the point name based on actual niagara proxy point using <i>v_$1_v</i> format.
     * @param point actual niagara proxy point name
     * @return virtual customized point name using <i>v_$1_v</i> format
     */
    public static String wrapPoint(String point) {
        String result = point.replaceAll(Constants.ORIGINAL_POINT_REGEX, Constants.WRAPPER_POINT);

        return result;
    }

    /**
     * Unwrap the virtual point name to actual niagara proxy point name.
     * @param wrappedPoint virtual customized point name
     * @return actual niagara proxy point name
     */
    public static String unwrapperPoint(String wrappedPoint) {
        String result = wrappedPoint.replaceAll(Constants.WRAPPER_POINT_REGEX, Constants.UNWRAPPER_POINT);

        return result;
    }

}
