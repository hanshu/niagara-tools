package com.tridiumap.niagaraProxyEdge.util;

import com.tridiumap.niagaraProxyEdge.util.StringUtils;

import javax.baja.control.BBooleanPoint;
import javax.baja.control.BControlPoint;
import javax.baja.control.BNumericPoint;
import javax.baja.log.Log;
import javax.baja.status.BStatusBoolean;
import javax.baja.status.BStatusNumeric;
import javax.baja.sys.*;
import java.util.logging.Logger;

/**
 * ControlPointInvoker is used to actually invoke the <i>set</i> action of niagara proxy point.
 */
public class ControlPointInvoker {
    private static final Logger LOG = Logger.getLogger("niagaraProxyEdge.invoker");

    public void execute(Property property, BControlPoint point) {
        // find the related niagara proxy point by its name
        String niagaraProxyPoint = StringUtils.unwrapperPoint(point.getName());
        LOG.fine("target proxy point name: " + niagaraProxyPoint);

        BComponent base = point.getParentComponent();
        Property targetProp = base.getParent().getProperty(niagaraProxyPoint);
        if(!targetProp.getType().is(BControlPoint.TYPE)) return;

        LOG.fine("target proxy point: " + targetProp.toString());
        // invoke the set action based on actual niagara proxy point
        BControlPoint target = (BControlPoint)base.getParent().get(targetProp);
        if(!target.isRunning()) return;
        if(!target.getStatus().isValid()) return;

        Action set = target.getAction("set");
        if(set!=null) {
            LOG.fine(target.getName() + " subscribed: " + target.isSubscribed());
            if(point.getType().is(BNumericPoint.TYPE))
                invokeNumeric(point, property, target, set);
            else if(point.getType().is(BBooleanPoint.TYPE))
                invokeBoolean(point, property, target, set);
        }
    }

    private void invokeNumeric(BControlPoint wrapper, Property property, BControlPoint target, Action set) {
        BStatusNumeric value = (BStatusNumeric)wrapper.get(property);
        target.invoke(set, BDouble.make(value.getNumeric()), null);
    }

    private void invokeBoolean(BControlPoint wrapper, Property property, BControlPoint target, Action set) {
        BStatusBoolean value = (BStatusBoolean)wrapper.get(property);
        target.invoke(set, BBoolean.make(value.getBoolean()), null);
    }
}
