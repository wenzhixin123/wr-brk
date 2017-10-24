package com.sinotrans.gd.wlp.common.service;



import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.common.model.LocationTaskModel;

public abstract interface StockWorkManager extends BaseManager
{
  public abstract LocationTaskModel setStockWork(LocationTaskModel paramLocationTaskModel);

  public abstract boolean PorcessRemainHold();

  public abstract boolean PorcessRemainHoldInLodUuid(String paramString1, String paramString2);

  public abstract boolean delrOpt(LocationTaskModel paramLocationTaskModel);

  public abstract boolean checkPanelNo(String paramString1, String paramString2);

  public abstract boolean DelSPU(String paramString);
}

/* Location:           C:\Users\Administrator\Desktop\wms_stockwork.jar
 * Qualified Name:     com.sinotrans.gd.wms.stockwork.service.StockWorkManager
 * JD-Core Version:    0.6.2
 */