package com.tridiumap.niagaraProxyEdge.points;

import com.tridiumap.niagaraProxyEdge.interfaces.BINiagaraProxyPoint;
import com.tridiumap.niagaraProxyEdge.util.ControlPointInvoker;

import javax.baja.control.BNumericWritable;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;

@NiagaraType
public class BNiagaraProxyPointNumericWritable extends BNumericWritable implements BINiagaraProxyPoint {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.niagaraProxyEdge.points.BNiagaraProxyPointNumericWritable(2979906276)1.0$ @*/
/* Generated Fri Mar 02 17:30:01 CST 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BNiagaraProxyPointNumericWritable.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    private ControlPointInvoker invoker;

    @Override
    public void started() throws Exception {
        super.started();
        invoker = new ControlPointInvoker();
    }

    @Override
    public void changed(Property property, Context context) {
        super.changed(property, context);

        if(!isRunning()) return;

        if(property.equals(in1)||property.equals(in2)||property.equals(in3)||property.equals(in4)||property.equals(in5)||property.equals(in6)
                ||property.equals(in7)||property.equals(in8)||property.equals(in9)||property.equals(in10)||property.equals(in11)
                ||property.equals(in12)||property.equals(in13)||property.equals(in14)||property.equals(in15)||property.equals(in16)) {

            if(invoker!=null)
                invoker.execute(property, this);
        }
    }
}
