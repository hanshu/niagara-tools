package com.tridiumap.niagaraProxyEdge;

import com.tridium.nd.point.BNiagaraProxyExt;
import com.tridium.util.ArrayUtil;
import com.tridiumap.niagaraProxyEdge.points.BNiagaraProxyPointBooleanWritable;
import com.tridiumap.niagaraProxyEdge.points.BNiagaraProxyPointNumericWritable;
import com.tridiumap.niagaraProxyEdge.points.BNiagaraProxyPointProxyExt;
import com.tridiumap.niagaraProxyEdge.util.StringUtils;

import javax.baja.control.*;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.status.BStatusBoolean;
import javax.baja.status.BStatusNumeric;
import javax.baja.status.BStatusValue;
import javax.baja.sys.*;
import java.util.logging.Logger;

@NiagaraType
@NiagaraAction(name = "execute")
public class BNiagaraProxyPointConverter extends BComponent {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.niagaraProxyEdge.BNiagaraProxyPointConverter(231401340)1.0$ @*/
/* Generated Mon Mar 05 16:09:05 CST 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Action "execute"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code execute} action.
   * @see #execute()
   */
  public static final Action execute = newAction(0, null);
  
  /**
   * Invoke the {@code execute} action.
   * @see #execute
   */
  public void execute() { invoke(execute, null, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BNiagaraProxyPointConverter.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
    private static final BIcon ICON = BIcon.std("linkFrom.png");
    private static final Logger LOG = Logger.getLogger("niagaraProxyEdge.converter");

    private NiagaraProxyPointSubscriber subscriber;

    public BIcon getIcon() { return ICON; }

    @Override
    public void started() throws Exception {
        super.started();

        subscriber = new NiagaraProxyPointSubscriber();

        subscribeNiagaraProxyPoints();
    }

    private void subscribeNiagaraProxyPoints() {
        BComponent parent = (BComponent)getParent();
        LOG.fine("parent: " + parent.toPathString());
        // iterate all our niagara proxy control points
        BControlPoint[] points = parent.getChildren(BControlPoint.class);
        for(BControlPoint niagaraProxyPoint : points) {
            if(niagaraProxyPoint.getProxyExt().getType().is(BNiagaraProxyExt.TYPE)) {
                Action set = niagaraProxyPoint.getAction("set");
                if(set!=null) {
                    subscriber.subscribe(niagaraProxyPoint);
                }
            }
        }
    }

    @Override
    public void stopped() throws Exception {
        super.stopped();

        if(subscriber!=null) {
            subscriber.unsubscribeAll();
            subscriber = null;
        }
    }

    public void doExecute() {
        // subscribe all niagara proxy points
        subscribeNiagaraProxyPoints();

        BComponent parent = (BComponent)getParent();
        // iterate all control points
        BControlPoint[] points = parent.getChildren(BControlPoint.class);
        for(BControlPoint point : points) {
            // skip while niagara proxy point existed
            if(parent.get(StringUtils.wrapPoint(point.getName()))!=null) continue;
            // find niagara proxy points
            if(point.getProxyExt().getType().is(BNiagaraProxyExt.TYPE)) {
                Action set = point.getAction("set");
                LOG.fine("set action: " + set);
                if (set != null) {
                    BControlPoint proxyPoint = null;
                    if (point.getType().is(BNumericPoint.TYPE)) {
                        proxyPoint = new BNiagaraProxyPointNumericWritable();
                        proxyPoint.setProxyExt(new BNiagaraProxyPointProxyExt());

                        setNumericOut(point, proxyPoint);
                    }
                    else if (point.getType().is(BBooleanPoint.TYPE)) {
                        proxyPoint = new BNiagaraProxyPointBooleanWritable();
                        proxyPoint.setProxyExt(new BNiagaraProxyPointProxyExt());

                        setBooleanOut(point, proxyPoint);
                    }

                    if (proxyPoint != null) {
                        parent.add(StringUtils.wrapPoint(point.getName()), proxyPoint);
                    }
                }
            }
        }
    }

    private <T extends BControlPoint> void setOutValue(BStatusValue out, BControlPoint source, T vp) {
        BNiagaraProxyPointProxyExt proxyExt = (BNiagaraProxyPointProxyExt)vp.getProxyExt();
        proxyExt.readOk(out);
    }

    private class NiagaraProxyPointSubscriber extends Subscriber {
        protected NiagaraProxyPointSubscriber() {
        }

        public void event(BComponentEvent event) {
            LOG.fine(event.getId() + " " + event.toString());

            switch(event.getId()) {
                case BComponentEvent.PROPERTY_CHANGED:
                    BControlPoint source = (BControlPoint)event.getSourceComponent();
                    LOG.fine("property changed component: " + source.toDebugString() + ", event slot: " + event.getSlotName());
                    if(event.getSlot().equals(source.getOutProperty())) {
                        BStatusValue value = source.getOutStatusValue();
                        // find proxy point
                        String pName = StringUtils.wrapPoint(source.getName());
                        BControlPoint vp = (BControlPoint)source.getParent().get(pName);
                        if(vp!=null) {
                            if(source.getType().is(BNumericPoint.TYPE)) {
                                setNumericOut(source, vp);
                            }
                            else if(source.getType().is(BBooleanPoint.TYPE)) {
                                setBooleanOut(source, vp);
                            }
                        }
                    }

                    break;
            }
        }
    }

    private void setBooleanOut(BControlPoint source, BControlPoint vp) {
        BStatusBoolean out = (BStatusBoolean)source.get(source.getOutProperty());
        LOG.fine("BBooleanPoint out: " + out.getBoolean());
        setOutValue(out, source, vp);
    }

    private void setNumericOut(BControlPoint source, BControlPoint vp) {
        BStatusNumeric out = (BStatusNumeric)source.get(source.getOutProperty());
        LOG.fine("BNumericPoint out: " + out.getNumeric());
        setOutValue(out, source, vp);
    }
}
