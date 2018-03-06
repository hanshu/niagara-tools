package com.tridiumap.niagaraProxyEdge.points;

import com.tridium.nd.point.BNiagaraPointDeviceExt;

import javax.baja.driver.point.BProxyExt;
import javax.baja.driver.point.BReadWriteMode;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Context;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

@NiagaraType
public class BNiagaraProxyPointProxyExt extends BProxyExt {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.tridiumap.niagaraProxyEdge.points.BNiagaraProxyPointProxyExt(2979906276)1.0$ @*/
/* Generated Mon Mar 05 11:33:26 CST 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BNiagaraProxyPointProxyExt.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    @Override  public boolean write(Context context) throws Exception  {
        return false;
    }

    @Override  public void readSubscribed(Context context) throws Exception  {  }
    @Override  public void readUnsubscribed(Context context) throws Exception  {  }

    @Override
    public Type getDeviceExtType() {
        return BNiagaraPointDeviceExt.TYPE;
    }

    @Override  public BReadWriteMode getMode()  {
        return BReadWriteMode.readonly;
    }
}
