

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.sinotrans.gd.wlp.common.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.dao.NativeSqlDao;
import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.gd.wlp.common.model.LocPlanDetailModel;
import com.sinotrans.gd.wlp.common.model.LocationTaskModel;
import com.sinotrans.gd.wlp.common.model.LogisticsOrderDetailModel;
import com.sinotrans.gd.wlp.common.model.LogisticsOrderModel;
import com.sinotrans.gd.wlp.common.model.RemainHoldModel;
import com.sinotrans.gd.wlp.common.model.RemainSinworkModel;
import com.sinotrans.gd.wlp.common.model.StockWorkModel;
import com.sinotrans.gd.wlp.common.service.SQLQueryManager;
import com.sinotrans.gd.wlp.common.service.StockWorkManager;
import com.sinotrans.gd.wlp.common.service.WmsCommonManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.util.StringUtil;

@Service
public class StockWorkManagerImpl extends BaseManagerImpl implements StockWorkManager {
    @Autowired
    private NativeSqlDao nativeSqlDao;
    @Autowired
    private SQLQueryManager sqlQueryManager;
    @Autowired
    private WmsCommonManager wmsCommonManager;
   /* @Autowired
    private LocPlanConfigManager locPlanConfigManager;*/

    public StockWorkManagerImpl() {
    }

    private void toZero(LocationTaskModel lt) {
        lt.setHeight(Double.valueOf(StringUtil.doubleTo0(lt.getHeight())));
        lt.setLength(Double.valueOf(StringUtil.doubleTo0(lt.getLength())));
        lt.setWidth(Double.valueOf(StringUtil.doubleTo0(lt.getWidth())));
        lt.setGrossWeight(Double.valueOf(StringUtil.doubleTo0(lt.getGrossWeight())));
        lt.setVolume(Double.valueOf(StringUtil.doubleTo0(lt.getVolume())));
        lt.setQty(Double.valueOf(StringUtil.doubleTo0(lt.getQty())));
        lt.setSecondQty(Double.valueOf(StringUtil.doubleTo0(lt.getSecondQty())));
        lt.setThirdQty(Double.valueOf(StringUtil.doubleTo0(lt.getThirdQty())));
    }

    public LocationTaskModel setStockWork(LocationTaskModel lt) {
        this.toZero(lt);
        String locTaskUuid = lt.getLocationTaskUuid();
        String lodUuid = lt.getLogisticsOrderDetailUuid();
        String inLodUuid = lt.getInLogisticsOrderDetailUuid();
        String locTaskType = StringUtil.toTrim(lt.getLocTaskType());
        double qty = StringUtil.doubleTo0(lt.getQty());
        if(StringUtil.isNull(locTaskUuid)) {
            throw new ApplicationException("LocTaskUuid为空");
        } else if(StringUtil.isNull(lodUuid)) {
            throw new ApplicationException("LogisticsOrderDetailUuid为空");
        } else if(StringUtil.isNull(inLodUuid)) {
            throw new ApplicationException("InLogisticsOrderDetailUuid为空");
        } else if(qty <= 0.0D) {
            throw new ApplicationException("数量小于等于0");
        } else if(StringUtil.isNull(lt.getTargetLotCode())) {
            throw new ApplicationException("货位为空");
        } else if(StringUtil.isNull(lt.getOfficeCode())) {
            throw new ApplicationException("组织或所属公司为空");
        } else {
            if(locTaskType.equals("RECE")) {
                lt = this.rcecOpt(lt);
            } else if(locTaskType.equals("CANR")) {
                lt = this.canrOpt(lt);
            } else if(locTaskType.equals("ModLt")) {
                lt = this.ModLtOpt(lt);
            } else if(locTaskType.equals("REMS")) {
                lt = this.remsOpt(lt);
            } else if(locTaskType.equals("STOR")) {
                lt = this.storOpt(lt);
            } else if(locTaskType.equals("CANS")) {
                lt = this.cansOpt(lt);
            } else if(locTaskType.equals("LOCT")) {
                lt = this.loctOpt(lt);
            } else if(locTaskType.equals("ADJT")) {
                lt = this.adjtOpt(lt);
            } else if(locTaskType.equals("CADJ")) {
                lt = this.cadjOpt(lt);
            } else if(locTaskType.equals("PALT")) {
                lt = this.paltOpt(lt);
            } else if(locTaskType.equals("PROC")) {
                lt = this.procOpt(lt);
            } else if(locTaskType.equals("CPRO")) {
                lt = this.cproOpt(lt);
            } else if(locTaskType.equals("PPRO")) {
                lt = this.pproOpt(lt);
            } else if(locTaskType.equals("UNPA")) {
                lt = this.unpaOpt(lt);
            } else if(locTaskType.equals("CNPA")) {
                lt = this.cnpaOpt(lt);
            } else if(locTaskType.equals("NATU")) {
                lt = this.natuOpt(lt);
            } else if(!locTaskType.equals("WORK") && !locTaskType.equals("CANWO")) {
                if(locTaskType.equals("DSOC")) {
                    lt = this.dsocOpt(lt);
                } else if(locTaskType.equals("CSOC")) {
                    lt = this.csocOpt(lt);
                } else if(locTaskType.equals("STUP")) {
                    lt = this.stupOpt(lt);
                } else if(locTaskType.equals("CASP")) {
                    lt = this.caspOpt(lt);
                } else if(locTaskType.equals("PICK")) {
                    lt = this.pickOpt(lt);
                } else if(locTaskType.equals("CANP")) {
                    lt = this.canpOpt(lt);
                } else if(locTaskType.equals("OUTV")) {
                    lt = this.outvOpt(lt);
                } else if(locTaskType.equals("CANV")) {
                    lt = this.canvOpt(lt);
                } else if(locTaskType.equals("DDEV")) {
                    lt = this.ddevOpt(lt);
                } else if(locTaskType.equals("CDDE")) {
                    lt = this.cddeOpt(lt);
                } else if(locTaskType.equals("BUYA")) {
                    lt = this.buyaOpt(lt);
                } else {
                    if(!locTaskType.equals("BUYC")) {
                        throw new ApplicationException("无法识别" + locTaskType + "的操作");
                    }

                    lt = this.buycOpt(lt);
                }
            } else {
                lt = this.workOpt(lt);
            }

            return lt;
        }
    }

    private LocationTaskModel rcecOpt(LocationTaskModel lt) {
        if(!lt.getLogisticsOrderDetailUuid().equals(lt.getInLogisticsOrderDetailUuid())) {
            throw new ApplicationException("InLogisticsOrderDetailUuid和LogisticsOrderDetailUuid数据不相同，无法操作");
        } else {
            String ifTargetLotCodeSql;
            String ifTargetLotCode_flag;
            if(!StringUtil.isNull(lt.getBarcode())) {
                ifTargetLotCodeSql = "select \'x\' from remain_sinwork rs where rs.barcode=\'" + lt.getBarcode() + "\' and rs.office_code=\'" + lt.getOfficeCode() + "\' ";
                ifTargetLotCode_flag = this.sqlQueryManager.getColumnData(ifTargetLotCodeSql, "", "");
                if(!StringUtil.isNull(ifTargetLotCode_flag)) {
                    throw new ApplicationException("收货操作--当前条码[" + lt.getBarcode() + "]在库存中已存在");
                }
            }

            ifTargetLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getTargetLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
            ifTargetLotCode_flag = this.sqlQueryManager.getColumnData(ifTargetLotCodeSql, "", "");
            if(StringUtil.isNull(ifTargetLotCode_flag)) {
                throw new ApplicationException("收货操作--当前货位[" + lt.getTargetLotCode() + "]不正确");
            } else {
                String CW3_Sql = "select lod.logistics_order_no from logistics_order_detail lod,logistics_order lo   where lo.logistics_order_uuid=lod.logistics_order_uuid  and lod.logistics_order_detail_uuid=\'" + lt.getLogisticsOrderDetailUuid() + "\' " + " and ( substrb(nvl(lod.control_word,\'000\'),3,1)=\'F\' or " + " substrb(nvl(lo.control_word,\'000\'),3,1)=\'F\' ) " + " and lo.transaction_type=\'SIN\' ";
                String CW3_flag = this.sqlQueryManager.getColumnData(CW3_Sql, "", "");
                if(!StringUtil.isNull(CW3_flag)) {
                    throw new ApplicationException("收货操作--[" + CW3_flag + "]单已完结，不允许执行收货操作");
                } else {
                    String inStockWorkUuid = this.checkRemainSinwork(lt);
                    String locTaskUuid = lt.getLocationTaskUuid();
                    double qty = StringUtil.doubleTo0(lt.getQty());
                    double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                    double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                    double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                    double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                    double volume = StringUtil.doubleTo0(lt.getVolume());
                    String lotCode = lt.getTargetLotCode();
                    String officeCode = lt.getOfficeCode();
                    StockWorkModel sw = new StockWorkModel();
                    sw.setLocationTaskUuid(locTaskUuid);
                    sw.setInStockWorkUuid(inStockWorkUuid);
                    sw.setStockDate(this.sqlQueryManager.getSysDate(""));
                    sw.setStockType("SIN");
                    sw.setStockDesc("收货");
                    sw.setQty(Double.valueOf(qty));
                    sw.setSecondQty(Double.valueOf(secondQty));
                    sw.setThirdQty(Double.valueOf(thirdQty));
                    sw.setNetWeight(Double.valueOf(netWeight));
                    sw.setGrossWeight(Double.valueOf(grossWeight));
                    sw.setVolume(Double.valueOf(volume));
                    sw.setLotCode(lotCode);
                    sw.setLastStockWorkUuid("");
                    sw.setControlWord(StringUtil.fixKey("", 20, "0"));
                    sw.setOfficeCode(officeCode);
                    sw = (StockWorkModel)this.dao.save(sw);
                    if(StringUtil.isNull(inStockWorkUuid)) {
                        inStockWorkUuid = sw.getStockWorkUuid();
                        sw.setInStockWorkUuid(inStockWorkUuid);
                        sw = (StockWorkModel)this.dao.save(sw);
                    }

                    lt.setInStockWorkUuid(inStockWorkUuid);
                    lt = (LocationTaskModel)this.dao.save(lt);
                    sw = this.setInRemainSinwork(lt, sw);
                    this.setInRemainHold(lt, sw);
                    new LogisticsOrderDetailModel();
                    List l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).query();
                    if(l2 != null && l2.size() > 0) {
                        LogisticsOrderDetailModel lod = (LogisticsOrderDetailModel)l2.get(0);
                        lod.setConfirmedQty(Double.valueOf(StringUtil.doubleTo0(lod.getConfirmedQty()) + StringUtil.doubleTo0(sw.getQty())));
                        lod.setConfirmedQty(StringUtil.ObjectToDouble(lod.getConfirmedQty(), 6));
                        String loUuid;
                        if(lod.getConfirmedQty().doubleValue() > lod.getQty().doubleValue() + 1.0E-6D) {
                            loUuid = "select bcd.display_value from BAS_CODE_DEF bcd,BAS_CODE_TYPE bct  where bcd.bas_code_type_uuid=bct.bas_code_type_uuid  and bct.type_code=\'RECE_YN\'  and bcd.code_value=\'收货是否允许超过计划数(Y/N)\'  and bcd.status=\'Active\'  and bcd.office_code=\'" + officeCode + "\' ";
                            String lo = this.sqlQueryManager.getColumnData(loUuid, "", "");
                            if(StringUtil.isNull(lo) || !StringUtil.toUpper(lo).equals("Y")) {
                                throw new ApplicationException("收货操作--收货数量大于计划数量");
                            }
                        }

                        lod = (LogisticsOrderDetailModel)this.dao.save(lod);
                        loUuid = lod.getLogisticsOrderUuid();
                        if(!StringUtil.isNull(loUuid)) {
                            LogisticsOrderModel lo1 = (LogisticsOrderModel)this.dao.get(LogisticsOrderModel.class, loUuid);
                            if(lo1.getCutOffDate() == null) {
                                if(lt.getLocTaskDate() == null) {
                                    lo1.setCutOffDate(this.dao.getSysDate());
                                } else {
                                    lo1.setCutOffDate(lt.getLocTaskDate());
                                }

                                lo1 = (LogisticsOrderModel)this.dao.save(lo1);
                            }

                            return lt;
                        } else {
                            throw new ApplicationException("收货操作--loUuid为空");
                        }
                    } else {
                        throw new ApplicationException("收货操作--无法检索到logisticsOrderDetailUuid");
                    }
                }
            }
        }
    }

    private LocationTaskModel canrOpt(LocationTaskModel lt) {
        if(!lt.getLogisticsOrderDetailUuid().equals(lt.getInLogisticsOrderDetailUuid())) {
            throw new ApplicationException("InLogisticsOrderDetailUuid和LogisticsOrderDetailUuid数据不相同，无法操作");
        } else if(StringUtil.isNull(lt.getLastLocationTaskUuid())) {
            throw new ApplicationException("取消收货--lastLocTaskUuid为空");
        } else if(StringUtil.isNull(lt.getInStockWorkUuid())) {
            throw new ApplicationException("取消收货--InStockWorkUuid为空");
        } else {
            String ifHoldQtyFlag = this.checkRemainHoldQty(lt);
            if(StringUtil.isNull(ifHoldQtyFlag)) {
                throw new ApplicationException("取消收货--已办理出库作业单,库存的可出库不够,无法取消收货");
            } else {
                String CW3_Sql = "select lod.logistics_order_no from logistics_order_detail lod,logistics_order lo   where lo.logistics_order_uuid=lod.logistics_order_uuid  and lod.logistics_order_detail_uuid=\'" + lt.getLogisticsOrderDetailUuid() + "\' " + " and ( substrb(nvl(lod.control_word,\'000\'),3,1)=\'F\' or " + " substrb(nvl(lo.control_word,\'000\'),3,1)=\'F\' ) ";
                String CW3_flag = this.sqlQueryManager.getColumnData(CW3_Sql, "", "");
                if(!StringUtil.isNull(CW3_flag)) {
                    throw new ApplicationException("取消收货--[" + CW3_flag + "]单已完结，不允许执行取消收货操作");
                } else {
                    String inStockWorkUuid_sql = "select SW.STOCK_WORK_UUID   from location_task lt,stock_work sw,remain_sinwork rs  where lt.location_task_uuid=sw.location_task_uuid    and sw.stock_work_uuid=rs.in_stock_work_uuid    and sw.stock_type=\'SIN\'    and lt.loc_task_type=\'RECE\'    and lt.qty - nvl(rs.remain_qty,0) >=0    and lt.location_task_uuid=\'" + lt.getLastLocationTaskUuid() + "\'" + "   and rownum=1 ";
                    if(StringUtil.isNull(lt.getBarcode())) {
                        inStockWorkUuid_sql = " select rs.in_STOCK_WORK_UUID  from location_task lt,remain_sinwork rs   where lt.in_stock_work_uuid=rs.in_stock_work_uuid   and lt.loc_task_type=\'RECE\'   and nvl(rs.remain_qty,0) >= " + lt.getQty() + " and lt.location_task_uuid=\'" + lt.getLastLocationTaskUuid() + "\' ";
                    }

                    String inStockWorkUuid = this.sqlQueryManager.getColumnData(inStockWorkUuid_sql, "", "");
                    if(StringUtil.isNull(inStockWorkUuid)) {
                        throw new ApplicationException("取消收货--要取消收货记录的库存数量不够或已做下一步操作(例如：上架等操作)");
                    } else if(!lt.getInStockWorkUuid().equals(inStockWorkUuid)) {
                        throw new ApplicationException("取消收货--InStockWorkUuid写入不对，不是原收货InStockWorkUuid");
                    } else {
                        String locTaskUuid = lt.getLocationTaskUuid();
                        double qty = StringUtil.doubleTo0(lt.getQty());
                        double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                        double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                        double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                        double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                        double volume = StringUtil.doubleTo0(lt.getVolume());
                        String lotCode = StringUtil.toTrim(lt.getTargetLotCode());
                        String officeCode = lt.getOfficeCode();
                        StockWorkModel sw = new StockWorkModel();
                        sw.setLocationTaskUuid(locTaskUuid);
                        sw.setInStockWorkUuid(inStockWorkUuid);
                        sw.setStockDate(this.sqlQueryManager.getSysDate(""));
                        sw.setStockType("SOT");
                        sw.setStockDesc("取消收货");
                        sw.setQty(Double.valueOf(qty));
                        sw.setSecondQty(Double.valueOf(secondQty));
                        sw.setThirdQty(Double.valueOf(thirdQty));
                        sw.setNetWeight(Double.valueOf(netWeight));
                        sw.setGrossWeight(Double.valueOf(grossWeight));
                        sw.setVolume(Double.valueOf(volume));
                        sw.setLotCode(lotCode);
                        sw.setLastStockWorkUuid(inStockWorkUuid);
                        sw.setControlWord(StringUtil.fixKey("", 20, "0"));
                        sw.setOfficeCode(officeCode);
                        sw = (StockWorkModel)this.dao.save(sw);
                        lt = this.setOutRemainSinwork(lt, sw);
                        sw.setNetWeight(lt.getNetWeight());
                        sw.setGrossWeight(lt.getGrossWeight());
                        sw.setVolume(lt.getVolume());
                        this.setOutRemainHold(lt, sw);
                        lt = (LocationTaskModel)this.dao.save(lt);
                        new LogisticsOrderDetailModel();
                        List l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
                        if(l2 != null && l2.size() > 0) {
                            LogisticsOrderDetailModel lod = (LogisticsOrderDetailModel)l2.get(0);
                            lod.setConfirmedQty(Double.valueOf(StringUtil.doubleTo0(lod.getConfirmedQty()) - StringUtil.doubleTo0(sw.getQty())));
                            lod.setConfirmedQty(StringUtil.ObjectToDouble(lod.getConfirmedQty(), 6));
                            if(lod.getConfirmedQty().doubleValue() < 0.0D) {
                                throw new ApplicationException("取消收货--已收货数量小于0");
                            } else {
                                lod = (LogisticsOrderDetailModel)this.dao.save(lod);
                                String loUuid = lod.getLogisticsOrderUuid();
                                if(!StringUtil.isNull(loUuid)) {
                                    if(this.getLodConfirmedQty(loUuid) <= 0.0D) {
                                        LogisticsOrderModel lo = (LogisticsOrderModel)this.dao.get(LogisticsOrderModel.class, loUuid);
                                        if(lo.getCutOffDate() != null) {
                                            lo.setCutOffDate((Date)null);
                                            lo = (LogisticsOrderModel)this.dao.save(lo);
                                        }
                                    }

                                    return lt;
                                } else {
                                    throw new ApplicationException("取消收货--loUuid为空");
                                }
                            }
                        } else {
                            throw new ApplicationException("取消收货--无法检索到logisticsOrderDetailUuid");
                        }
                    }
                }
            }
        }
    }

    private LocationTaskModel ModLtOpt(LocationTaskModel lt) {
        if(StringUtil.isNull(lt.getLastLocationTaskUuid())) {
            throw new ApplicationException("LastLocationTaskUuid为空");
        } else {
            List modiList = this.dao.createCommonQuery(LocationTaskModel.class).addCondition(Condition.eq("locationTaskUuid", lt.getLastLocationTaskUuid())).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
            if(modiList != null && modiList.size() > 0) {
                LocationTaskModel modilt = (LocationTaskModel)modiList.get(0);
                String modi_bz = "";
                double modiVolume = StringUtil.doubleTo0(lt.getVolume());
                double modiGrossweight = StringUtil.doubleTo0(lt.getGrossWeight());
                double modiLength = StringUtil.doubleTo0(lt.getLength());
                double modiWidth = StringUtil.doubleTo0(lt.getWidth());
                double modiHeight = StringUtil.doubleTo0(lt.getHeight());
                if(modiVolume > 0.0D) {
                    modilt.setVolume(Double.valueOf(modiVolume));
                    modi_bz = "Y";
                }

                if(modiGrossweight > 0.0D) {
                    modilt.setGrossWeight(Double.valueOf(modiGrossweight));
                    modi_bz = "Y";
                }

                if(modiLength > 0.0D) {
                    modilt.setLength(Double.valueOf(modiLength));
                    modi_bz = "Y";
                }

                if(modiWidth > 0.0D) {
                    modilt.setWidth(Double.valueOf(modiWidth));
                    modi_bz = "Y";
                }

                if(modiHeight > 0.0D) {
                    modilt.setHeight(Double.valueOf(modiHeight));
                    modi_bz = "Y";
                }

                if(modi_bz.equals("Y")) {
                    modilt = (LocationTaskModel)this.dao.save(modilt);
                }

                return lt;
            } else {
                throw new ApplicationException("修改操作记录 --无法检索到locationTaskUuid");
            }
        }
    }

    private LocationTaskModel remsOpt(LocationTaskModel lt) {
        String inLodUuid = lt.getInLogisticsOrderDetailUuid();
        double lod_qty = StringUtil.doubleTo0(lt.getQty());
        double lod_volume = StringUtil.doubleTo0(lt.getVolume());
        double lod_grossweight = StringUtil.doubleTo0(lt.getGrossWeight());
        double lod_netweight = StringUtil.doubleTo0(lt.getNetWeight());
        double base_volume = -1.0D;
        double base_grossweight = -1.0D;
        double base_netweight = -1.0D;
        if(lod_volume > 0.0D) {
            base_volume = StringUtil.ObjectToDouble(Double.valueOf(lod_volume / lod_qty), 8).doubleValue();
        }

        if(lod_grossweight > 0.0D) {
            base_grossweight = StringUtil.ObjectToDouble(Double.valueOf(lod_grossweight / lod_qty), 8).doubleValue();
        }

        if(lod_netweight > 0.0D) {
            base_netweight = StringUtil.ObjectToDouble(Double.valueOf(lod_netweight / lod_qty), 8).doubleValue();
        }

        List ls_lt = this.dao.createCommonQuery(LocationTaskModel.class).addCondition(Condition.eq("inLogisticsOrderDetailUuid", inLodUuid)).addCondition(Condition.ne("locTaskType", "REMS")).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
        if(ls_lt != null && ls_lt.size() > 0) {
            for(int ls_rh = 0; ls_rh < ls_lt.size(); ++ls_rh) {
                LocationTaskModel ls_rs = (LocationTaskModel)ls_lt.get(ls_rh);
                if(base_volume > 0.0D) {
                    ls_rs.setVolume(StringUtil.ObjectToDouble(Double.valueOf(ls_rs.getQty().doubleValue() * base_volume), 6));
                }

                if(base_grossweight > 0.0D) {
                    ls_rs.setGrossWeight(StringUtil.ObjectToDouble(Double.valueOf(ls_rs.getQty().doubleValue() * base_grossweight), 6));
                }

                if(base_netweight > 0.0D) {
                    ls_rs.setNetWeight(StringUtil.ObjectToDouble(Double.valueOf(ls_rs.getQty().doubleValue() * base_netweight), 6));
                }

                ls_rs.setBatchNo(lt.getBatchNo());
                ls_rs.setItemCode(lt.getItemCode());
                ls_rs.setExtItemCode(lt.getExtItemCode());
                ls_rs.setGoodsDesc(lt.getGoodsDesc());
                ls_rs.setMarksNumber(lt.getMarksNumber());
                ls_rs.setPackageNo(lt.getPackageNo());
                ls_rs.setGoodsKind(lt.getGoodsKind());
                ls_rs.setGoodsNature(lt.getGoodsNature());
                ls_rs.setUnitCode(lt.getUnitCode());
                ls_rs.setUnitDesc(lt.getUnitDesc());
                ls_rs = (LocationTaskModel)this.dao.save(ls_rs);
            }

            List var22 = this.dao.createCommonQuery(RemainHoldModel.class).addCondition(Condition.eq("inLogisticsOrderDetailUuid", inLodUuid)).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
            if(var22 != null && var22.size() > 0) {
                for(int var23 = 0; var23 < var22.size(); ++var23) {
                    RemainHoldModel k = (RemainHoldModel)var22.get(var23);
                    if(base_volume > 0.0D) {
                        k.setInstockVolume(Double.valueOf(StringUtil.ObjectToDouble(k.getInstockVolume(), 6).doubleValue() - StringUtil.ObjectToDouble(k.getRemainVolume(), 6).doubleValue() + StringUtil.ObjectToDouble(Double.valueOf(k.getRemainQty().doubleValue() * base_volume), 6).doubleValue()));
                        k.setRemainVolume(StringUtil.ObjectToDouble(Double.valueOf(k.getRemainQty().doubleValue() * base_volume), 6));
                    }

                    if(base_grossweight > 0.0D) {
                        k.setInstockGrossWeight(Double.valueOf(StringUtil.ObjectToDouble(k.getInstockGrossWeight(), 6).doubleValue() - StringUtil.ObjectToDouble(k.getRemainGrossWeight(), 6).doubleValue() + StringUtil.ObjectToDouble(Double.valueOf(k.getRemainQty().doubleValue() * base_grossweight), 6).doubleValue()));
                        k.setRemainGrossWeight(StringUtil.ObjectToDouble(Double.valueOf(k.getRemainQty().doubleValue() * base_grossweight), 6));
                    }

                    if(base_netweight > 0.0D) {
                        k.setInstockNetWeight(Double.valueOf(StringUtil.ObjectToDouble(k.getInstockNetWeight(), 6).doubleValue() - StringUtil.ObjectToDouble(k.getRemainNetWeight(), 6).doubleValue() + StringUtil.ObjectToDouble(Double.valueOf(k.getRemainQty().doubleValue() * base_netweight), 6).doubleValue()));
                        k.setRemainNetWeight(StringUtil.ObjectToDouble(Double.valueOf(k.getRemainQty().doubleValue() * base_netweight), 6));
                    }

                    k.setBatchNo(lt.getBatchNo());
                    k.setItemCode(lt.getItemCode());
                    k.setExtItemCode(lt.getExtItemCode());
                    k.setGoodsDesc(lt.getGoodsDesc());
                    k.setMarksNumber(lt.getMarksNumber());
                    k.setPackageNo(lt.getPackageNo());
                    k.setGoodsKind(lt.getGoodsKind());
                    k.setGoodsNature(lt.getGoodsNature());
                    k.setInstockUnitCode(lt.getUnitCode());
                    k.setInstockUnitDesc(lt.getUnitDesc());
                    k = (RemainHoldModel)this.dao.save(k);
                }
            }

            List var24 = this.dao.createCommonQuery(RemainSinworkModel.class).addCondition(Condition.eq("inLogisticsOrderDetailUuid", inLodUuid)).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
            if(var24 != null && var24.size() > 0) {
                for(int var25 = 0; var25 < var24.size(); ++var25) {
                    RemainSinworkModel modifyRs = (RemainSinworkModel)var24.get(var25);
                    if(base_volume > 0.0D) {
                        modifyRs.setInstockVolume(Double.valueOf(StringUtil.ObjectToDouble(modifyRs.getInstockVolume(), 6).doubleValue() - StringUtil.ObjectToDouble(modifyRs.getRemainVolume(), 6).doubleValue() + StringUtil.ObjectToDouble(Double.valueOf(modifyRs.getRemainQty().doubleValue() * base_volume), 6).doubleValue()));
                        modifyRs.setRemainVolume(StringUtil.ObjectToDouble(Double.valueOf(modifyRs.getRemainQty().doubleValue() * base_volume), 6));
                    }

                    if(base_grossweight > 0.0D) {
                        modifyRs.setInstockGrossWeight(Double.valueOf(StringUtil.ObjectToDouble(modifyRs.getInstockGrossWeight(), 6).doubleValue() - StringUtil.ObjectToDouble(modifyRs.getRemainGrossWeight(), 6).doubleValue() + StringUtil.ObjectToDouble(Double.valueOf(modifyRs.getRemainQty().doubleValue() * base_grossweight), 6).doubleValue()));
                        modifyRs.setRemainGrossWeight(StringUtil.ObjectToDouble(Double.valueOf(modifyRs.getRemainQty().doubleValue() * base_grossweight), 6));
                    }

                    if(base_netweight > 0.0D) {
                        modifyRs.setInstockNetWeight(Double.valueOf(StringUtil.ObjectToDouble(modifyRs.getInstockNetWeight(), 6).doubleValue() - StringUtil.ObjectToDouble(modifyRs.getRemainNetWeight(), 6).doubleValue() + StringUtil.ObjectToDouble(Double.valueOf(modifyRs.getRemainQty().doubleValue() * base_netweight), 6).doubleValue()));
                        modifyRs.setRemainNetWeight(StringUtil.ObjectToDouble(Double.valueOf(modifyRs.getRemainQty().doubleValue() * base_netweight), 6));
                    }

                    modifyRs.setBatchNo(lt.getBatchNo());
                    modifyRs.setItemCode(lt.getItemCode());
                    modifyRs.setExtItemCode(lt.getExtItemCode());
                    modifyRs.setGoodsDesc(lt.getGoodsDesc());
                    modifyRs.setMarksNumber(lt.getMarksNumber());
                    modifyRs.setPackageNo(lt.getPackageNo());
                    modifyRs.setGoodsKind(lt.getGoodsKind());
                    modifyRs.setGoodsNature(lt.getGoodsNature());
                    modifyRs.setInstockUnitCode(lt.getUnitCode());
                    modifyRs.setInstockUnitDesc(lt.getUnitDesc());
                    modifyRs = (RemainSinworkModel)this.dao.save(modifyRs);
                }
            }
        }

        return lt;
    }

    public boolean delrOpt(LocationTaskModel lt) {
        boolean result = false;
        if(!lt.getLogisticsOrderDetailUuid().equals(lt.getInLogisticsOrderDetailUuid())) {
            throw new ApplicationException("取消收货--InLogisticsOrderDetailUuid和LogisticsOrderDetailUuid数据不相同，无法操作");
        } else if(StringUtil.isNull(lt.getLocationTaskUuid())) {
            throw new ApplicationException("取消收货--LocTaskUuid为空");
        } else if(StringUtil.isNull(lt.getInStockWorkUuid())) {
            throw new ApplicationException("取消收货--InStockWorkUuid为空");
        } else if(StringUtil.isNull(lt.getTargetLotCode())) {
            throw new ApplicationException("取消收货--货位为空");
        } else {
            String CW3_Sql = "select lod.logistics_order_no from logistics_order_detail lod,logistics_order lo   where lo.logistics_order_uuid=lod.logistics_order_uuid  and lod.logistics_order_detail_uuid=\'" + lt.getLogisticsOrderDetailUuid() + "\' " + " and ( substrb(nvl(lod.control_word,\'000\'),3,1)=\'F\' or " + " substrb(nvl(lo.control_word,\'000\'),3,1)=\'F\' ) ";
            String CW3_flag = this.sqlQueryManager.getColumnData(CW3_Sql, "", "");
            if(!StringUtil.isNull(CW3_flag)) {
                throw new ApplicationException("取消收货--[" + CW3_flag + "]单已完结，不允许执行取消收货操作");
            } else {
                String inStockWorkUuid_sql = "select SW.STOCK_WORK_UUID   from location_task lt,stock_work sw,remain_sinwork rs  where lt.location_task_uuid=sw.location_task_uuid    and sw.stock_work_uuid=rs.in_stock_work_uuid    and sw.stock_type=\'SIN\'    and lt.loc_task_type=\'RECE\'    and nvl(lt.qty,0) = nvl(rs.remain_qty,0)     and lt.location_task_uuid=\'" + lt.getLocationTaskUuid() + "\'" + "   and rownum=1 ";
                String inStockWorkUuid = this.sqlQueryManager.getColumnData(inStockWorkUuid_sql, "", "");
                if(StringUtil.isNull(inStockWorkUuid)) {
                    throw new ApplicationException("取消收货--要取消收货记录的库存数量不够或已做下一步操作(例如：上架或移位等操作)");
                } else if(!lt.getInStockWorkUuid().equals(inStockWorkUuid)) {
                    throw new ApplicationException("取消收货--库存的InStockWorkUuid与原lt收货InStockWorkUuid不一致");
                } else {
                    new StockWorkModel();
                    List l_sw = this.dao.createCommonQuery(StockWorkModel.class).addCondition(Condition.eq("stockWorkUuid", lt.getInStockWorkUuid())).addCondition(Condition.eq("stockType", "SIN")).query();
                    if(l_sw != null && l_sw.size() > 0) {
                        StockWorkModel sw = (StockWorkModel)l_sw.get(0);
                        if(StringUtil.equalsDouble(lt.getQty(), sw.getQty())) {
                            lt = this.setOutRemainSinwork(lt, sw);
                            sw.setNetWeight(lt.getNetWeight());
                            sw.setGrossWeight(lt.getGrossWeight());
                            sw.setVolume(lt.getVolume());
                            this.setOutRemainHold(lt, sw);
                            this.dao.remove(sw);
                            this.dao.remove(lt);
                            new LogisticsOrderDetailModel();
                            List l_lod = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).query();
                            if(l_lod != null && l_lod.size() > 0) {
                                LogisticsOrderDetailModel lod = (LogisticsOrderDetailModel)l_lod.get(0);
                                lod.setConfirmedQty(Double.valueOf(StringUtil.doubleTo0(lod.getConfirmedQty()) - StringUtil.doubleTo0(lt.getQty())));
                                lod.setConfirmedQty(StringUtil.ObjectToDouble(lod.getConfirmedQty(), 6));
                                if(lod.getConfirmedQty().doubleValue() < 0.0D) {
                                    throw new ApplicationException("取消收货--扣减已收货数量小于0");
                                } else {
                                    if(StringUtil.doubleTo0(lod.getConfirmedQty()) == 0.0D) {
                                        this.dao.remove(lod);
                                    }

                                    result = true;
                                    return result;
                                }
                            } else {
                                throw new ApplicationException("取消收货--没有检索到lod记录");
                            }
                        } else {
                            throw new ApplicationException("取消收货--lt数量与sw数量不一致");
                        }
                    } else {
                        throw new ApplicationException("取消收货--无法检索到SW记录");
                    }
                }
            }
        }
    }

    private LocationTaskModel storOpt(LocationTaskModel lt) {
        if(StringUtil.isNull(lt.getInLogisticsOrderDetailUuid())) {
            throw new ApplicationException("上架操作--InLogisticsOrderDetailUuid为空");
        } else if(StringUtil.isNull(lt.getSourceLotCode())) {
            throw new ApplicationException("上架操作--收货区为空");
        } else if(lt.getSourceLotCode().equals(lt.getTargetLotCode())) {
            throw new ApplicationException("移位操作--源货位与目的货位相同");
        } else {
            String ifTargetLotCodeSql;
            String ifTargetLotCode_flag;
            if(!StringUtil.isNull(lt.getBarcode())) {
                ifTargetLotCodeSql = "select \'xxx\' from location_task lt where lt.loc_task_type in (\'STOR\')  and lt.barcode = \'" + lt.getBarcode() + "\' " + "  and lt.office_code =\'" + lt.getOfficeCode() + "\' " + "  and lt.in_logistics_order_detail_uuid =\'" + lt.getInLogisticsOrderDetailUuid() + "\' " + "   and nvl(lt.qty,0) -  " + "   (select nvl(sum(c_lt.qty),0) from location_task c_lt where c_lt.last_location_task_uuid=lt.location_task_uuid " + "    and c_lt.loc_task_type in (\'CANS\') " + "     and c_lt.barcode =  \'" + lt.getBarcode() + "\' " + "    and c_lt.office_code = \'" + lt.getOfficeCode() + "\' " + "     and c_lt.in_logistics_order_detail_uuid =\'" + lt.getInLogisticsOrderDetailUuid() + "\' )>0 " + " having count(lt.location_task_uuid) >1   ";
                ifTargetLotCode_flag = this.sqlQueryManager.getColumnData(ifTargetLotCodeSql, "", "");
                if(!StringUtil.isNull(ifTargetLotCode_flag)) {
                    throw new ApplicationException("上架操作--已重复上架，请用移位功能");
                }
            }

            ifTargetLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getTargetLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
            ifTargetLotCode_flag = this.sqlQueryManager.getColumnData(ifTargetLotCodeSql, "", "");
            if(StringUtil.isNull(ifTargetLotCode_flag)) {
                throw new ApplicationException("上架操作--当前货位[" + lt.getTargetLotCode() + "]不正确");
            } else if(StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                throw new ApplicationException("上架--lastLocTaskUuid为空");
            } else {
                String inStockWorkUuid_sql = "select SW.STOCK_WORK_UUID   from location_task lt,stock_work sw,remain_sinwork rs  where lt.location_task_uuid=sw.location_task_uuid    and sw.stock_work_uuid=rs.in_stock_work_uuid    and sw.stock_type=\'SIN\'    and lt.loc_task_type<>\'PICK\'    and lt.qty - nvl(rs.remain_qty,0) >=0    and lt.location_task_uuid=\'" + lt.getLastLocationTaskUuid() + "\'" + "   and rownum=1 ";
                String inStockWorkUuid = this.sqlQueryManager.getColumnData(inStockWorkUuid_sql, "", "");
                if(StringUtil.isNull(inStockWorkUuid)) {
                    throw new ApplicationException("上架操作--收货记录的库存数量不够或已做下一步操作");
                } else if(!lt.getInStockWorkUuid().equals(inStockWorkUuid)) {
                    throw new ApplicationException("上架操作--InStockWorkUuid写入不对，不是原收货InStockWorkUuid");
                } else {
                    String locTaskUuid = lt.getLocationTaskUuid();
                    double qty = StringUtil.doubleTo0(lt.getQty());
                    double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                    double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                    double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                    double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                    double volume = StringUtil.doubleTo0(lt.getVolume());
                    String sourceLotCode = lt.getSourceLotCode();
                    String targetLotCode = lt.getTargetLotCode();
                    String lastLocTaskUuid = lt.getLastLocationTaskUuid();
                    StockWorkModel sw_out = new StockWorkModel();
                    sw_out.setLocationTaskUuid(locTaskUuid);
                    sw_out.setInStockWorkUuid(inStockWorkUuid);
                    sw_out.setStockDate(this.sqlQueryManager.getSysDate(""));
                    sw_out.setStockType("SOT");
                    sw_out.setStockDesc("上架");
                    sw_out.setQty(Double.valueOf(qty));
                    sw_out.setSecondQty(Double.valueOf(secondQty));
                    sw_out.setThirdQty(Double.valueOf(thirdQty));
                    sw_out.setNetWeight(Double.valueOf(netWeight));
                    sw_out.setGrossWeight(Double.valueOf(grossWeight));
                    sw_out.setVolume(Double.valueOf(volume));
                    sw_out.setLotCode(sourceLotCode);
                    sw_out.setLastStockWorkUuid(inStockWorkUuid);
                    sw_out.setControlWord(StringUtil.fixKey("", 20, "0"));
                    sw_out.setOfficeCode(lt.getOfficeCode());
                    sw_out = (StockWorkModel)this.dao.save(sw_out);
                    lt = this.setOutRemainSinwork(lt, sw_out);
                    inStockWorkUuid = "";
                    inStockWorkUuid = this.checkRemainSinwork(lt);
                    StockWorkModel sw_in = new StockWorkModel();
                    sw_in.setLocationTaskUuid(locTaskUuid);
                    sw_in.setStockDate(this.sqlQueryManager.getSysDate(""));
                    sw_in.setStockType("SIN");
                    sw_in.setStockDesc("上架");
                    sw_in.setQty(Double.valueOf(qty));
                    sw_in.setInStockWorkUuid(inStockWorkUuid);
                    sw_in.setSecondQty(Double.valueOf(secondQty));
                    sw_in.setThirdQty(Double.valueOf(thirdQty));
                    sw_in.setNetWeight(lt.getNetWeight());
                    sw_in.setGrossWeight(lt.getGrossWeight());
                    sw_in.setVolume(lt.getVolume());
                    sw_in.setLotCode(targetLotCode);
                    sw_in.setControlWord(StringUtil.fixKey("", 20, "0"));
                    sw_in.setOfficeCode(lt.getOfficeCode());
                    sw_in.setLastStockWorkUuid(sw_out.getStockWorkUuid());
                    sw_in = (StockWorkModel)this.dao.save(sw_in);
                    if(StringUtil.isNull(inStockWorkUuid)) {
                        inStockWorkUuid = sw_in.getStockWorkUuid();
                        sw_in.setInStockWorkUuid(inStockWorkUuid);
                    }

                    lt.setInStockWorkUuid(inStockWorkUuid);
                    lt = (LocationTaskModel)this.dao.save(lt);
                    sw_in = this.setInRemainSinwork(lt, sw_in);
                    new LogisticsOrderDetailModel();
                    List l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getInLogisticsOrderDetailUuid())).query();
                    if(l2 != null && l2.size() > 0) {
                        LogisticsOrderDetailModel lod = (LogisticsOrderDetailModel)l2.get(0);
                        lod.setDeliveredQty(Double.valueOf(StringUtil.doubleTo0(lod.getDeliveredQty()) + StringUtil.doubleTo0(sw_in.getQty())));
                        lod.setDeliveredQty(StringUtil.ObjectToDouble(lod.getDeliveredQty(), 6));
                        if(StringUtil.doubleTo0(lod.getDeliveredQty()) > StringUtil.doubleTo0(lod.getConfirmedQty())) {
                            throw new ApplicationException("上架--上架数量大于收货数量");
                        } else {
                            lod = (LogisticsOrderDetailModel)this.dao.save(lod);
                            return lt;
                        }
                    } else {
                        throw new ApplicationException("上架--无法检索到logisticsOrderDetailUuid");
                    }
                }
            }
        }
    }

    private LocationTaskModel cansOpt(LocationTaskModel lt) {
        if(StringUtil.isNull(lt.getInLogisticsOrderDetailUuid())) {
            throw new ApplicationException("取消上架操作--InLogisticsOrderDetailUuid为空");
        } else if(StringUtil.isNull(lt.getSourceLotCode())) {
            throw new ApplicationException("取消上架操作--货位为空");
        } else if(StringUtil.isNull(lt.getLastLocationTaskUuid())) {
            throw new ApplicationException("取消上架--lastLocTaskUuid为空");
        } else {
            String inStockWorkUuid_sql = "select SW.STOCK_WORK_UUID   from location_task lt,stock_work sw,remain_sinwork rs  where lt.location_task_uuid=sw.location_task_uuid    and sw.stock_work_uuid=rs.in_stock_work_uuid    and sw.stock_type=\'SIN\'    and lt.loc_task_type=\'STOR\'    and lt.qty - nvl(rs.remain_qty,0) >=0    and lt.location_task_uuid=\'" + lt.getLastLocationTaskUuid() + "\' " + "   and rownum=1 ";
            String inStockWorkUuid = this.sqlQueryManager.getColumnData(inStockWorkUuid_sql, "", "");
            if(StringUtil.isNull(inStockWorkUuid)) {
                throw new ApplicationException("取消上架--上架记录的库存数量不够或已做下一步操作");
            } else {
                String locTaskUuid = lt.getLocationTaskUuid();
                double qty = StringUtil.doubleTo0(lt.getQty());
                double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                double volume = StringUtil.doubleTo0(lt.getVolume());
                String sourceLotCode = lt.getSourceLotCode();
                String targetLotCode = lt.getTargetLotCode();
                String lastLocTaskUuid = lt.getLastLocationTaskUuid();
                List ls = this.dao.createCommonQuery(StockWorkModel.class).addCondition(Condition.eq("locationTaskUuid", lastLocTaskUuid)).query();
                if(ls != null && ls.size() >= 2) {
                    StockWorkModel sw_out = new StockWorkModel();
                    StockWorkModel sw_in = new StockWorkModel();

                    for(int lod = 0; lod < ls.size(); ++lod) {
                        if(((StockWorkModel)ls.get(lod)).getStockType().equals("SOT")) {
                            sw_in = (StockWorkModel)ls.get(lod);
                        } else {
                            if(!((StockWorkModel)ls.get(lod)).getStockType().equals("SIN")) {
                                throw new ApplicationException("取消上架--StockWork记录不符合要求");
                            }

                            sw_out = (StockWorkModel)ls.get(lod);
                            sw_out.setInStockWorkUuid(sw_out.getStockWorkUuid());
                        }
                    }

                    if(StringUtil.isNull(sw_out.getInStockWorkUuid())) {
                        throw new ApplicationException("取消上架--sot.InStockWorkUuid为空");
                    } else if(StringUtil.isNull(sw_out.getLotCode())) {
                        throw new ApplicationException("取消上架--sot.货位为空");
                    } else if(StringUtil.isNull(sw_in.getLotCode())) {
                        throw new ApplicationException("取消上架--sin.货位为空");
                    } else if(!sw_out.getInStockWorkUuid().equals(inStockWorkUuid)) {
                        throw new ApplicationException("取消上架--InStockWorkUuid写入不对，不是原上架InStockWorkUuid");
                    } else if(!lt.getInStockWorkUuid().equals(inStockWorkUuid)) {
                        throw new ApplicationException("取消上架--LT.InStockWorkUuid写入不对，");
                    } else {
                        sw_out.setStockWorkUuid("");
                        sw_out.setLocationTaskUuid(locTaskUuid);
                        sw_out.setStockDate(this.sqlQueryManager.getSysDate(""));
                        sw_out.setStockType("SOT");
                        sw_out.setStockDesc("取消上架");
                        sw_out.setQty(Double.valueOf(qty));
                        sw_out.setSecondQty(Double.valueOf(secondQty));
                        sw_out.setThirdQty(Double.valueOf(thirdQty));
                        sw_out.setNetWeight(Double.valueOf(netWeight));
                        sw_out.setGrossWeight(Double.valueOf(grossWeight));
                        sw_out.setVolume(Double.valueOf(volume));
                        sw_out.setLastStockWorkUuid("");
                        sw_out.setControlWord(StringUtil.fixKey("", 20, "0"));
                        sw_out.setOfficeCode(lt.getOfficeCode());
                        lt = this.setOutRemainSinwork(lt, sw_out);
                        lt = (LocationTaskModel)this.dao.save(lt);
                        sw_in.setStockWorkUuid("");
                        sw_in.setLocationTaskUuid(locTaskUuid);
                        sw_in.setStockDate(this.sqlQueryManager.getSysDate(""));
                        sw_in.setStockType("SIN");
                        sw_in.setStockDesc("取消上架");
                        sw_in.setQty(Double.valueOf(qty));
                        sw_in.setSecondQty(Double.valueOf(secondQty));
                        sw_in.setThirdQty(Double.valueOf(thirdQty));
                        sw_in.setNetWeight(lt.getNetWeight());
                        sw_in.setGrossWeight(lt.getGrossWeight());
                        sw_in.setVolume(lt.getVolume());
                        sw_in.setLastStockWorkUuid(sw_out.getStockWorkUuid());
                        sw_in.setControlWord(StringUtil.fixKey("", 20, "0"));
                        sw_in.setOfficeCode(lt.getOfficeCode());
                        sw_in = (StockWorkModel)this.dao.save(sw_in);
                        sw_in = this.setInRemainSinwork(lt, sw_in);
                        new LogisticsOrderDetailModel();
                        List l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getInLogisticsOrderDetailUuid())).query();
                        if(l2 != null && l2.size() > 0) {
                            LogisticsOrderDetailModel var25 = (LogisticsOrderDetailModel)l2.get(0);
                            var25.setDeliveredQty(Double.valueOf(StringUtil.doubleTo0(var25.getDeliveredQty()) - StringUtil.doubleTo0(sw_in.getQty())));
                            var25.setDeliveredQty(StringUtil.ObjectToDouble(var25.getDeliveredQty(), 6));
                            if(StringUtil.doubleTo0(var25.getDeliveredQty()) < 0.0D) {
                                throw new ApplicationException("取消上架--已上架数量小于0");
                            } else {
                                var25 = (LogisticsOrderDetailModel)this.dao.save(var25);
                                return lt;
                            }
                        } else {
                            throw new ApplicationException("取消上架--无法检索到logisticsOrderDetailUuid");
                        }
                    }
                } else {
                    throw new ApplicationException("取消上架--上架StockWork记录为空或StockWork记录小于2条记录");
                }
            }
        }
    }

    private LocationTaskModel loctOpt(LocationTaskModel lt) {
        if(StringUtil.isNull(lt.getSourceLotCode())) {
            throw new ApplicationException("移位操作--源货位为空");
        } else if(lt.getSourceLotCode().equals(lt.getTargetLotCode())) {
            throw new ApplicationException("移位操作--源货位与目的货位相同");
        } else {
            String ifSourceLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getSourceLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
            String ifSourceLotCode_flag = this.sqlQueryManager.getColumnData(ifSourceLotCodeSql, "", "");
            if(StringUtil.isNull(ifSourceLotCode_flag)) {
                throw new ApplicationException("移位操作--当前源货位[" + lt.getSourceLotCode() + "]不正确");
            } else {
                String ifTargetLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getTargetLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
                String ifTargetLotCode_flag = this.sqlQueryManager.getColumnData(ifTargetLotCodeSql, "", "");
                if(StringUtil.isNull(ifTargetLotCode_flag)) {
                    throw new ApplicationException("移位操作--当前目的货位[" + lt.getTargetLotCode() + "]不正确");
                } else {
                    String locTaskUuid = lt.getLocationTaskUuid();
                    double qty = StringUtil.doubleTo0(lt.getQty());
                    double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                    double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                    double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                    double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                    double volume = StringUtil.doubleTo0(lt.getVolume());
                    String sourceLotCode = lt.getSourceLotCode();
                    String targetLotCode = lt.getTargetLotCode();
                    String inStockWorkUuid = lt.getInStockWorkUuid();
                    if(StringUtil.isNull(inStockWorkUuid)) {
                        throw new ApplicationException("移位操作--inStockWorkUuid为空");
                    } else {
                        StockWorkModel sw_out = new StockWorkModel();
                        sw_out.setLocationTaskUuid(locTaskUuid);
                        sw_out.setInStockWorkUuid(inStockWorkUuid);
                        sw_out.setStockDate(this.sqlQueryManager.getSysDate(""));
                        sw_out.setStockType("SOT");
                        sw_out.setStockDesc("移位");
                        sw_out.setQty(Double.valueOf(qty));
                        sw_out.setSecondQty(Double.valueOf(secondQty));
                        sw_out.setThirdQty(Double.valueOf(thirdQty));
                        sw_out.setNetWeight(Double.valueOf(netWeight));
                        sw_out.setGrossWeight(Double.valueOf(grossWeight));
                        sw_out.setVolume(Double.valueOf(volume));
                        sw_out.setLotCode(sourceLotCode);
                        sw_out.setLastStockWorkUuid("");
                        sw_out.setControlWord(StringUtil.fixKey("", 20, "0"));
                        sw_out.setOfficeCode(lt.getOfficeCode());
                        if(StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                            lt.setLastLocationTaskUuid(this.getLastLocationTaskUuid(sw_out.getInStockWorkUuid()));
                        }

                        if(!StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                            new LocationTaskModel();
                            List ls_lt = this.dao.createCommonQuery(LocationTaskModel.class).addCondition(Condition.eq("locationTaskUuid", lt.getLastLocationTaskUuid())).query();
                            if(ls_lt != null && ls_lt.size() > 0) {
                                LocationTaskModel sw_in = (LocationTaskModel)ls_lt.get(0);
                                if(sw_in.getLocTaskType() != null && sw_in.getLocTaskType().equals("PICK")) {
                                    throw new ApplicationException("移位操作 -- 当前记录为拣货记录，不允许操作");
                                }
                            }
                        }

                        lt = this.setOutRemainSinwork(lt, sw_out);
                        StockWorkModel sw_in1 = new StockWorkModel();
                        sw_in1.setLocationTaskUuid(locTaskUuid);
                        sw_in1.setInStockWorkUuid(inStockWorkUuid);
                        sw_in1.setStockDate(this.sqlQueryManager.getSysDate(""));
                        sw_in1.setStockType("SIN");
                        sw_in1.setStockDesc("移位");
                        sw_in1.setQty(Double.valueOf(qty));
                        sw_in1.setSecondQty(Double.valueOf(secondQty));
                        sw_in1.setThirdQty(Double.valueOf(thirdQty));
                        sw_in1.setNetWeight(lt.getNetWeight());
                        sw_in1.setGrossWeight(lt.getGrossWeight());
                        sw_in1.setVolume(lt.getVolume());
                        sw_in1.setLotCode(targetLotCode);
                        sw_in1.setLastStockWorkUuid("");
                        sw_in1.setControlWord(StringUtil.fixKey("", 20, "0"));
                        sw_in1.setOfficeCode(lt.getOfficeCode());
                        sw_in1.setLastStockWorkUuid(sw_out.getStockWorkUuid());
                        sw_in1 = (StockWorkModel)this.dao.save(sw_in1);
                        sw_in1.setInStockWorkUuid(sw_in1.getStockWorkUuid());
                        lt.setInStockWorkUuid(sw_in1.getStockWorkUuid());
                        lt = (LocationTaskModel)this.dao.save(lt);
                        this.setInRemainSinwork(lt, sw_in1);
                        this.checkLtPanelNo(lt);
                        return lt;
                    }
                }
            }
        }
    }

    private LocationTaskModel adjtOpt(LocationTaskModel lt) {
        if(lt.getLogisticsOrderDetailUuid().equals(lt.getInLogisticsOrderDetailUuid())) {
            throw new ApplicationException("InLogisticsOrderDetailUuid和LogisticsOrderDetailUuid数据相同，无法操作");
        } else if(StringUtil.isNull(lt.getSourceLotCode())) {
            throw new ApplicationException("移位单核销 -- 源货位为空");
        } else if(lt.getSourceLotCode().equals(lt.getTargetLotCode())) {
            throw new ApplicationException("移位单核销 -- 源货位与目的货位相同");
        } else {
            String ifSourceLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getSourceLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
            String ifSourceLotCode_flag = this.sqlQueryManager.getColumnData(ifSourceLotCodeSql, "", "");
            if(StringUtil.isNull(ifSourceLotCode_flag)) {
                throw new ApplicationException("移位单核销 -- 当前源货位[" + lt.getSourceLotCode() + "]不正确");
            } else {
                String ifTargetLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getTargetLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
                String ifTargetLotCode_flag = this.sqlQueryManager.getColumnData(ifTargetLotCodeSql, "", "");
                if(StringUtil.isNull(ifTargetLotCode_flag)) {
                    throw new ApplicationException("移位单核销 -- 当前目的货位[" + lt.getTargetLotCode() + "]不正确");
                } else {
                    String locTaskUuid = lt.getLocationTaskUuid();
                    double qty = StringUtil.doubleTo0(lt.getQty());
                    double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                    double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                    double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                    double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                    double volume = StringUtil.doubleTo0(lt.getVolume());
                    String sourceLotCode = lt.getSourceLotCode();
                    String targetLotCode = lt.getTargetLotCode();
                    String inStockWorkUuid = lt.getInStockWorkUuid();
                    if(StringUtil.isNull(inStockWorkUuid)) {
                        throw new ApplicationException("移位单核销 -- inStockWorkUuid为空");
                    } else {
                        StockWorkModel sw_out = new StockWorkModel();
                        sw_out.setLocationTaskUuid(locTaskUuid);
                        sw_out.setInStockWorkUuid(inStockWorkUuid);
                        sw_out.setStockDate(this.sqlQueryManager.getSysDate(""));
                        sw_out.setStockType("SOT");
                        sw_out.setStockDesc("移位单核销");
                        sw_out.setQty(Double.valueOf(qty));
                        sw_out.setSecondQty(Double.valueOf(secondQty));
                        sw_out.setThirdQty(Double.valueOf(thirdQty));
                        sw_out.setNetWeight(Double.valueOf(netWeight));
                        sw_out.setGrossWeight(Double.valueOf(grossWeight));
                        sw_out.setVolume(Double.valueOf(volume));
                        sw_out.setLotCode(sourceLotCode);
                        sw_out.setLastStockWorkUuid("");
                        sw_out.setControlWord(StringUtil.fixKey("", 20, "0"));
                        sw_out.setOfficeCode(lt.getOfficeCode());
                        if(StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                            lt.setLastLocationTaskUuid(this.getLastLocationTaskUuid(sw_out.getInStockWorkUuid()));
                        }

                        if(!StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                            new LocationTaskModel();
                            List lod = this.dao.createCommonQuery(LocationTaskModel.class).addCondition(Condition.eq("locationTaskUuid", lt.getLastLocationTaskUuid())).query();
                            if(lod != null && lod.size() > 0) {
                                LocationTaskModel sw_in = (LocationTaskModel)lod.get(0);
                                if(sw_in.getLocTaskType() != null && sw_in.getLocTaskType().equals("PICK")) {
                                    throw new ApplicationException("移位单核销 -- 当前记录为拣货记录，不允许操作");
                                }
                            }
                        }

                        lt = this.setOutRemainSinwork(lt, sw_out);
                        StockWorkModel sw_in1 = new StockWorkModel();
                        sw_in1.setLocationTaskUuid(locTaskUuid);
                        sw_in1.setInStockWorkUuid(inStockWorkUuid);
                        sw_in1.setStockDate(this.sqlQueryManager.getSysDate(""));
                        sw_in1.setStockType("SIN");
                        sw_in1.setStockDesc("移位单核销");
                        sw_in1.setQty(Double.valueOf(qty));
                        sw_in1.setSecondQty(Double.valueOf(secondQty));
                        sw_in1.setThirdQty(Double.valueOf(thirdQty));
                        sw_in1.setNetWeight(lt.getNetWeight());
                        sw_in1.setGrossWeight(lt.getGrossWeight());
                        sw_in1.setVolume(lt.getVolume());
                        sw_in1.setLotCode(targetLotCode);
                        sw_in1.setLastStockWorkUuid("");
                        sw_in1.setControlWord(StringUtil.fixKey("", 20, "0"));
                        sw_in1.setOfficeCode(lt.getOfficeCode());
                        sw_in1.setLastStockWorkUuid(sw_out.getStockWorkUuid());
                        sw_in1 = (StockWorkModel)this.dao.save(sw_in1);
                        sw_in1.setInStockWorkUuid(sw_in1.getStockWorkUuid());
                        lt.setInStockWorkUuid(sw_in1.getStockWorkUuid());
                        lt = (LocationTaskModel)this.dao.save(lt);
                        sw_in1 = this.setInRemainSinwork(lt, sw_in1);
                        new LogisticsOrderDetailModel();
                        List l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).query();
                        if(l2 != null && l2.size() > 0) {
                            LogisticsOrderDetailModel lod1 = (LogisticsOrderDetailModel)l2.get(0);
                            lod1.setConfirmedQty(Double.valueOf(StringUtil.doubleTo0(lod1.getConfirmedQty()) + StringUtil.doubleTo0(sw_in1.getQty())));
                            lod1.setConfirmedQty(StringUtil.ObjectToDouble(lod1.getConfirmedQty(), 6));
                            if(lod1.getConfirmedQty().doubleValue() > lod1.getQty().doubleValue() + 1.0E-6D) {
                                throw new ApplicationException("移位单核销 -- 移位数量大于计划数量");
                            } else {
                                lod1 = (LogisticsOrderDetailModel)this.dao.save(lod1);
                                return lt;
                            }
                        } else {
                            throw new ApplicationException("移位单核销 -- 无法检索到logisticsOrderDetailUuid");
                        }
                    }
                }
            }
        }
    }

    private LocationTaskModel cadjOpt(LocationTaskModel lt) {
        if(lt.getLogisticsOrderDetailUuid().equals(lt.getInLogisticsOrderDetailUuid())) {
            throw new ApplicationException("InLogisticsOrderDetailUuid和LogisticsOrderDetailUuid数据相同，无法操作");
        } else if(StringUtil.isNull(lt.getSourceLotCode())) {
            throw new ApplicationException("移位单反核销 -- 源货位为空");
        } else if(lt.getSourceLotCode().equals(lt.getTargetLotCode())) {
            throw new ApplicationException("移位单反核销 -- 源货位与目的货位相同");
        } else {
            String ifSourceLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getSourceLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
            String ifSourceLotCode_flag = this.sqlQueryManager.getColumnData(ifSourceLotCodeSql, "", "");
            if(StringUtil.isNull(ifSourceLotCode_flag)) {
                throw new ApplicationException("移位单反核销 -- 当前源货位[" + lt.getSourceLotCode() + "]不正确");
            } else {
                String ifTargetLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getTargetLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
                String ifTargetLotCode_flag = this.sqlQueryManager.getColumnData(ifTargetLotCodeSql, "", "");
                if(StringUtil.isNull(ifTargetLotCode_flag)) {
                    throw new ApplicationException("移位单反核销 -- 当前目的货位[" + lt.getTargetLotCode() + "]不正确");
                } else {
                    String locTaskUuid = lt.getLocationTaskUuid();
                    double qty = StringUtil.doubleTo0(lt.getQty());
                    double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                    double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                    double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                    double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                    double volume = StringUtil.doubleTo0(lt.getVolume());
                    String sourceLotCode = lt.getSourceLotCode();
                    String targetLotCode = lt.getTargetLotCode();
                    String inStockWorkUuid = lt.getInStockWorkUuid();
                    if(StringUtil.isNull(inStockWorkUuid)) {
                        throw new ApplicationException("移位单反核销 -- inStockWorkUuid为空");
                    } else {
                        StockWorkModel sw_out = new StockWorkModel();
                        sw_out.setLocationTaskUuid(locTaskUuid);
                        sw_out.setInStockWorkUuid(inStockWorkUuid);
                        sw_out.setStockDate(this.sqlQueryManager.getSysDate(""));
                        sw_out.setStockType("SOT");
                        sw_out.setStockDesc("移位单反核销");
                        sw_out.setQty(Double.valueOf(qty));
                        sw_out.setSecondQty(Double.valueOf(secondQty));
                        sw_out.setThirdQty(Double.valueOf(thirdQty));
                        sw_out.setNetWeight(Double.valueOf(netWeight));
                        sw_out.setGrossWeight(Double.valueOf(grossWeight));
                        sw_out.setVolume(Double.valueOf(volume));
                        sw_out.setLotCode(sourceLotCode);
                        sw_out.setLastStockWorkUuid("");
                        sw_out.setControlWord(StringUtil.fixKey("", 20, "0"));
                        sw_out.setOfficeCode(lt.getOfficeCode());
                        if(StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                            lt.setLastLocationTaskUuid(this.getLastLocationTaskUuid(sw_out.getInStockWorkUuid()));
                        }

                        if(!StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                            new LocationTaskModel();
                            List lod = this.dao.createCommonQuery(LocationTaskModel.class).addCondition(Condition.eq("locationTaskUuid", lt.getLastLocationTaskUuid())).query();
                            if(lod != null && lod.size() > 0) {
                                LocationTaskModel sw_in = (LocationTaskModel)lod.get(0);
                                if(sw_in.getLocTaskType() != null && sw_in.getLocTaskType().equals("PICK")) {
                                    throw new ApplicationException("移位单反核销 -- 当前记录为拣货记录，不允许操作");
                                }
                            }
                        }

                        lt = this.setOutRemainSinwork(lt, sw_out);
                        StockWorkModel sw_in1 = new StockWorkModel();
                        sw_in1.setLocationTaskUuid(locTaskUuid);
                        sw_in1.setInStockWorkUuid(inStockWorkUuid);
                        sw_in1.setStockDate(this.sqlQueryManager.getSysDate(""));
                        sw_in1.setStockType("SIN");
                        sw_in1.setStockDesc("移位单反核销");
                        sw_in1.setQty(Double.valueOf(qty));
                        sw_in1.setSecondQty(Double.valueOf(secondQty));
                        sw_in1.setThirdQty(Double.valueOf(thirdQty));
                        sw_in1.setNetWeight(lt.getNetWeight());
                        sw_in1.setGrossWeight(lt.getGrossWeight());
                        sw_in1.setVolume(lt.getVolume());
                        sw_in1.setLotCode(targetLotCode);
                        sw_in1.setLastStockWorkUuid("");
                        sw_in1.setControlWord(StringUtil.fixKey("", 20, "0"));
                        sw_in1.setOfficeCode(lt.getOfficeCode());
                        sw_in1.setLastStockWorkUuid(sw_out.getStockWorkUuid());
                        sw_in1 = (StockWorkModel)this.dao.save(sw_in1);
                        sw_in1.setInStockWorkUuid(sw_in1.getStockWorkUuid());
                        lt.setInStockWorkUuid(sw_in1.getStockWorkUuid());
                        lt = (LocationTaskModel)this.dao.save(lt);
                        sw_in1 = this.setInRemainSinwork(lt, sw_in1);
                        new LogisticsOrderDetailModel();
                        List l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).query();
                        if(l2 != null && l2.size() > 0) {
                            LogisticsOrderDetailModel lod1 = (LogisticsOrderDetailModel)l2.get(0);
                            lod1.setConfirmedQty(Double.valueOf(StringUtil.doubleTo0(lod1.getConfirmedQty()) - StringUtil.doubleTo0(sw_in1.getQty())));
                            lod1.setConfirmedQty(StringUtil.ObjectToDouble(lod1.getConfirmedQty(), 6));
                            if(lod1.getConfirmedQty().doubleValue() < 0.0D) {
                                throw new ApplicationException("移位单反核销 -- 移位核销数量大于0");
                            } else {
                                lod1 = (LogisticsOrderDetailModel)this.dao.save(lod1);
                                return lt;
                            }
                        } else {
                            throw new ApplicationException("移位单反核销 -- 无法检索到logisticsOrderDetailUuid");
                        }
                    }
                }
            }
        }
    }

    private LocationTaskModel paltOpt(LocationTaskModel lt) {
        if(StringUtil.isNull(lt.getControlWord())) {
            throw new ApplicationException("移拼货物操作--ControlWord为空");
        } else if(!lt.getControlWord().substring(0, 1).equals("S") && !lt.getControlWord().substring(0, 1).equals("T")) {
            throw new ApplicationException("移拼货物操作--ControlWord不符合要求");
        } else {
            String ifTargetLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getTargetLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
            String ifTargetLotCode_flag = this.sqlQueryManager.getColumnData(ifTargetLotCodeSql, "", "");
            if(StringUtil.isNull(ifTargetLotCode_flag)) {
                throw new ApplicationException("移拼货物操作--当前货位[" + lt.getTargetLotCode() + "]不正确");
            } else {
                String locTaskUuid = lt.getLocationTaskUuid();
                double qty = StringUtil.doubleTo0(lt.getQty());
                double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                double volume = StringUtil.doubleTo0(lt.getVolume());
                String targetLotCode = lt.getTargetLotCode();
                String inStockWorkUuid = lt.getInStockWorkUuid();
                StockWorkModel sw_in;
                if(lt.getControlWord().substring(0, 1).equals("S")) {
                    if(StringUtil.isNull(inStockWorkUuid)) {
                        throw new ApplicationException("移拼货物操作--inStockWorkUuid为空");
                    }

                    sw_in = new StockWorkModel();
                    sw_in.setLocationTaskUuid(locTaskUuid);
                    sw_in.setInStockWorkUuid(inStockWorkUuid);
                    sw_in.setStockDate(this.sqlQueryManager.getSysDate(""));
                    sw_in.setStockType("SOT");
                    sw_in.setStockDesc("移拼货物");
                    sw_in.setQty(Double.valueOf(qty));
                    sw_in.setSecondQty(Double.valueOf(secondQty));
                    sw_in.setThirdQty(Double.valueOf(thirdQty));
                    sw_in.setNetWeight(Double.valueOf(netWeight));
                    sw_in.setGrossWeight(Double.valueOf(grossWeight));
                    sw_in.setVolume(Double.valueOf(volume));
                    sw_in.setLotCode(targetLotCode);
                    sw_in.setLastStockWorkUuid("");
                    sw_in.setControlWord(StringUtil.fixKey("", 20, "0"));
                    sw_in.setOfficeCode(lt.getOfficeCode());
                    if(StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                        lt.setLastLocationTaskUuid(this.getLastLocationTaskUuid(sw_in.getInStockWorkUuid()));
                    }

                    if(!StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                        new LocationTaskModel();
                        List ls_lt = this.dao.createCommonQuery(LocationTaskModel.class).addCondition(Condition.eq("locationTaskUuid", lt.getLastLocationTaskUuid())).query();
                        if(ls_lt != null && ls_lt.size() > 0) {
                            LocationTaskModel ifLtPick_flag = (LocationTaskModel)ls_lt.get(0);
                            if(ifLtPick_flag.getLocTaskType() != null && ifLtPick_flag.getLocTaskType().equals("PICK")) {
                                throw new ApplicationException("移拼货物操作 -- 当前源货物为拣货记录，不允许操作");
                            }
                        }
                    }

                    lt = this.setOutRemainSinwork(lt, sw_in);
                    sw_in.setNetWeight(lt.getNetWeight());
                    sw_in.setGrossWeight(lt.getGrossWeight());
                    sw_in.setVolume(lt.getVolume());
                    this.setOutRemainHold(lt, sw_in);
                    lt = (LocationTaskModel)this.dao.save(lt);
                } else if(lt.getControlWord().substring(0, 1).equals("T")) {
                    if(!StringUtil.isNull(inStockWorkUuid)) {
                        String sw_in1 = "select lt.location_task_uuid from location_task lt,stock_work sw,remain_sinwork rs  where lt.location_task_uuid=sw.location_task_uuid  and sw.stock_work_uuid=rs.in_stock_work_uuid  and lt.loc_task_type=\'PICK\'  and rs.in_stock_work_uuid=\'" + inStockWorkUuid + "\' ";
                        String ifLtPick_flag1 = this.sqlQueryManager.getColumnData(sw_in1, "", "");
                        if(!StringUtil.isNull(ifLtPick_flag1)) {
                            throw new ApplicationException("移拼货物操作--当前目的货物已拣货，不能移拼货物操作！");
                        }
                    }

                    sw_in = new StockWorkModel();
                    sw_in.setLocationTaskUuid(locTaskUuid);
                    sw_in.setInStockWorkUuid(inStockWorkUuid);
                    sw_in.setStockDate(this.sqlQueryManager.getSysDate(""));
                    sw_in.setStockType("SIN");
                    sw_in.setStockDesc("移拼货物");
                    sw_in.setQty(Double.valueOf(qty));
                    sw_in.setSecondQty(Double.valueOf(secondQty));
                    sw_in.setThirdQty(Double.valueOf(thirdQty));
                    sw_in.setNetWeight(Double.valueOf(netWeight));
                    sw_in.setGrossWeight(Double.valueOf(grossWeight));
                    sw_in.setVolume(Double.valueOf(volume));
                    sw_in.setLotCode(targetLotCode);
                    sw_in.setLastStockWorkUuid("");
                    sw_in.setControlWord(StringUtil.fixKey("", 20, "0"));
                    sw_in.setOfficeCode(lt.getOfficeCode());
                    sw_in.setLastStockWorkUuid("");
                    sw_in = (StockWorkModel)this.dao.save(sw_in);
                    if(StringUtil.isNull(inStockWorkUuid)) {
                        sw_in.setInStockWorkUuid(sw_in.getStockWorkUuid());
                        lt.setInStockWorkUuid(sw_in.getStockWorkUuid());
                        lt = (LocationTaskModel)this.dao.save(lt);
                    }

                    sw_in = this.setInRemainSinwork(lt, sw_in);
                    this.setInRemainHold(lt, sw_in);
                }

                return lt;
            }
        }
    }

    private LocationTaskModel procOpt(LocationTaskModel lt) {
        if(StringUtil.isNull(lt.getControlWord())) {
            throw new ApplicationException("加工操作--ControlWord为空");
        } else if(!lt.getControlWord().substring(0, 1).equals("S") && !lt.getControlWord().substring(0, 1).equals("T")) {
            throw new ApplicationException("加工操作--ControlWord不符合要求");
        } else {
            String ifTargetLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getTargetLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
            String ifTargetLotCode_flag = this.sqlQueryManager.getColumnData(ifTargetLotCodeSql, "", "");
            if(StringUtil.isNull(ifTargetLotCode_flag)) {
                throw new ApplicationException("加工操作--当前货位[" + lt.getTargetLotCode() + "]不正确");
            } else {
                String locTaskUuid = lt.getLocationTaskUuid();
                double qty = StringUtil.doubleTo0(lt.getQty());
                double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                double volume = StringUtil.doubleTo0(lt.getVolume());
                String targetLotCode = lt.getTargetLotCode();
                String inStockWorkUuid = lt.getInStockWorkUuid();
                StockWorkModel sw_in;
                LogisticsOrderDetailModel lod;
                List l2;
                if(lt.getControlWord().substring(0, 1).equals("S")) {
                    if(StringUtil.isNull(lt.getInStockWorkUuid())) {
                        throw new ApplicationException("加工操作--源货物inStockWorkUuid为空");
                    }

                    sw_in = new StockWorkModel();
                    sw_in.setLocationTaskUuid(locTaskUuid);
                    sw_in.setInStockWorkUuid(inStockWorkUuid);
                    sw_in.setStockDate(this.sqlQueryManager.getSysDate(""));
                    sw_in.setStockType("SOT");
                    sw_in.setStockDesc("加工");
                    sw_in.setQty(Double.valueOf(qty));
                    sw_in.setSecondQty(Double.valueOf(secondQty));
                    sw_in.setThirdQty(Double.valueOf(thirdQty));
                    sw_in.setNetWeight(Double.valueOf(netWeight));
                    sw_in.setGrossWeight(Double.valueOf(grossWeight));
                    sw_in.setVolume(Double.valueOf(volume));
                    sw_in.setLotCode(targetLotCode);
                    sw_in.setLastStockWorkUuid("");
                    sw_in.setControlWord(StringUtil.fixKey("", 20, "0"));
                    sw_in.setOfficeCode(lt.getOfficeCode());
                    lt = this.setOutRemainSinwork(lt, sw_in);
                    sw_in.setNetWeight(lt.getNetWeight());
                    sw_in.setGrossWeight(lt.getGrossWeight());
                    sw_in.setVolume(lt.getVolume());
                    this.setOutRemainHoldUuid(lt, sw_in);
                    lt = (LocationTaskModel)this.dao.save(lt);
                    new LogisticsOrderDetailModel();
                    l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).query();
                    if(l2 == null || l2.size() <= 0) {
                        throw new ApplicationException("加工操作--无法检索到源材料logisticsOrderDetailUuid");
                    }

                    lod = (LogisticsOrderDetailModel)l2.get(0);
                    lod.setDeliveredQty(Double.valueOf(StringUtil.doubleTo0(lod.getDeliveredQty()) + sw_in.getQty().doubleValue()));
                    lod.setDeliveredQty(StringUtil.ObjectToDouble(lod.getDeliveredQty(), 6));
                    lod = (LogisticsOrderDetailModel)this.dao.save(lod);
                    String loUuid = lod.getLogisticsOrderUuid();
                    if(StringUtil.isNull(loUuid)) {
                        throw new ApplicationException("加工核销操作--loUuid为空");
                    }

                    LogisticsOrderModel lo = (LogisticsOrderModel)this.dao.get(LogisticsOrderModel.class, loUuid);
                    if(lo.getCutOffDate() == null) {
                        if(lt.getLocTaskDate() == null) {
                            lo.setCutOffDate(this.dao.getSysDate());
                        } else {
                            lo.setCutOffDate(lt.getLocTaskDate());
                        }

                        lo = (LogisticsOrderModel)this.dao.save(lo);
                    }
                } else if(lt.getControlWord().substring(0, 1).equals("T")) {
                    sw_in = new StockWorkModel();
                    sw_in.setLocationTaskUuid(locTaskUuid);
                    sw_in.setInStockWorkUuid(inStockWorkUuid);
                    sw_in.setStockDate(this.sqlQueryManager.getSysDate(""));
                    sw_in.setStockType("SIN");
                    sw_in.setStockDesc("加工");
                    sw_in.setQty(Double.valueOf(qty));
                    sw_in.setSecondQty(Double.valueOf(secondQty));
                    sw_in.setThirdQty(Double.valueOf(thirdQty));
                    sw_in.setNetWeight(Double.valueOf(netWeight));
                    sw_in.setGrossWeight(Double.valueOf(grossWeight));
                    sw_in.setVolume(Double.valueOf(volume));
                    sw_in.setLotCode(targetLotCode);
                    sw_in.setLastStockWorkUuid("");
                    sw_in.setControlWord(StringUtil.fixKey("", 20, "0"));
                    sw_in.setOfficeCode(lt.getOfficeCode());
                    sw_in.setLastStockWorkUuid("");
                    sw_in = (StockWorkModel)this.dao.save(sw_in);
                    sw_in.setInStockWorkUuid(sw_in.getStockWorkUuid());
                    lt.setInStockWorkUuid(sw_in.getStockWorkUuid());
                    lt = (LocationTaskModel)this.dao.save(lt);
                    sw_in = this.setInRemainSinwork(lt, sw_in);
                    this.setInRemainHold(lt, sw_in);
                    new LogisticsOrderDetailModel();
                    l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).query();
                    if(l2 == null || l2.size() <= 0) {
                        throw new ApplicationException("加工操作--无法检索到成品logisticsOrderDetailUuid");
                    }

                    lod = (LogisticsOrderDetailModel)l2.get(0);
                    lod.setConfirmedQty(Double.valueOf(StringUtil.doubleTo0(lod.getConfirmedQty()) + StringUtil.doubleTo0(sw_in.getQty())));
                    lod.setConfirmedQty(StringUtil.ObjectToDouble(lod.getConfirmedQty(), 6));
                    lod = (LogisticsOrderDetailModel)this.dao.save(lod);
                }

                return lt;
            }
        }
    }

    private LocationTaskModel cproOpt(LocationTaskModel lt) {
        if(StringUtil.isNull(lt.getControlWord())) {
            throw new ApplicationException("加工撤单--ControlWord为空");
        } else if(!lt.getControlWord().substring(0, 1).equals("S") && !lt.getControlWord().substring(0, 1).equals("T")) {
            throw new ApplicationException("加工撤单--ControlWord不符合要求");
        } else {
            String ifTargetLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getTargetLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
            String ifTargetLotCode_flag = this.sqlQueryManager.getColumnData(ifTargetLotCodeSql, "", "");
            if(StringUtil.isNull(ifTargetLotCode_flag)) {
                throw new ApplicationException("加工撤单--当前货位[" + lt.getTargetLotCode() + "]不正确");
            } else {
                String locTaskUuid = lt.getLocationTaskUuid();
                double qty = StringUtil.doubleTo0(lt.getQty());
                double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                double volume = StringUtil.doubleTo0(lt.getVolume());
                String targetLotCode = lt.getTargetLotCode();
                String inStockWorkUuid = lt.getInStockWorkUuid();
                if(lt.getControlWord().substring(0, 1).equals("S")) {
                    if(StringUtil.isNull(lt.getInStockWorkUuid())) {
                        throw new ApplicationException("加工撤单--源货物inStockWorkUuid为空");
                    }

                    StockWorkModel new_locTaskUuid1 = new StockWorkModel();
                    new_locTaskUuid1.setLocationTaskUuid(locTaskUuid);
                    new_locTaskUuid1.setInStockWorkUuid(inStockWorkUuid);
                    new_locTaskUuid1.setStockDate(this.sqlQueryManager.getSysDate(""));
                    new_locTaskUuid1.setStockType("SOT");
                    new_locTaskUuid1.setStockDesc("加工撤单");
                    new_locTaskUuid1.setQty(Double.valueOf(qty));
                    new_locTaskUuid1.setSecondQty(Double.valueOf(secondQty));
                    new_locTaskUuid1.setThirdQty(Double.valueOf(thirdQty));
                    new_locTaskUuid1.setNetWeight(Double.valueOf(netWeight));
                    new_locTaskUuid1.setGrossWeight(Double.valueOf(grossWeight));
                    new_locTaskUuid1.setVolume(Double.valueOf(volume));
                    new_locTaskUuid1.setLotCode(targetLotCode);
                    new_locTaskUuid1.setLastStockWorkUuid("");
                    new_locTaskUuid1.setControlWord(StringUtil.fixKey("", 20, "0"));
                    new_locTaskUuid1.setOfficeCode(lt.getOfficeCode());
                    lt = this.setOutRemainSinwork(lt, new_locTaskUuid1);
                    new_locTaskUuid1.setNetWeight(lt.getNetWeight());
                    new_locTaskUuid1.setGrossWeight(lt.getGrossWeight());
                    new_locTaskUuid1.setVolume(lt.getVolume());
                    this.setOutRemainHold(lt, new_locTaskUuid1);
                    lt = (LocationTaskModel)this.dao.save(lt);
                    new LogisticsOrderDetailModel();
                    List sw_in1 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).query();
                    if(sw_in1 == null || sw_in1.size() <= 0) {
                        throw new ApplicationException("加工撤单--无法检索到成品logisticsOrderDetailUuid");
                    }

                    LogisticsOrderDetailModel ltrs1 = (LogisticsOrderDetailModel)sw_in1.get(0);
                    ltrs1.setConfirmedQty(Double.valueOf(StringUtil.doubleTo0(ltrs1.getConfirmedQty()) - StringUtil.doubleTo0(new_locTaskUuid1.getQty())));
                    ltrs1.setConfirmedQty(StringUtil.ObjectToDouble(ltrs1.getConfirmedQty(), 6));
                    ltrs1 = (LogisticsOrderDetailModel)this.dao.save(ltrs1);
                } else if(lt.getControlWord().substring(0, 1).equals("T")) {
                    String new_locTaskUuid = "";
                    StockWorkManagerImpl.LTRemain ltrs = this.getHbRemainData(lt);
                    new_locTaskUuid = ltrs.getLocationTaskUuid();
                    if(!StringUtil.isNull(new_locTaskUuid)) {
                        lt.setInStockWorkUuid(ltrs.getInStockWorkUuid());
                        lt.setTargetLotCode(ltrs.getLotCode());
                        lt.setInLogisticsOrderDetailUuid(ltrs.getInLogisticsOrderDetailUuid());
                        inStockWorkUuid = lt.getInStockWorkUuid();
                        targetLotCode = lt.getTargetLotCode();
                        locTaskUuid = new_locTaskUuid;
                    }

                    StockWorkModel sw_in = new StockWorkModel();
                    sw_in.setLocationTaskUuid(locTaskUuid);
                    sw_in.setInStockWorkUuid(inStockWorkUuid);
                    sw_in.setStockDate(this.sqlQueryManager.getSysDate(""));
                    sw_in.setStockType("SIN");
                    sw_in.setStockDesc("加工撤单");
                    sw_in.setQty(Double.valueOf(qty));
                    sw_in.setSecondQty(Double.valueOf(secondQty));
                    sw_in.setThirdQty(Double.valueOf(thirdQty));
                    sw_in.setNetWeight(Double.valueOf(netWeight));
                    sw_in.setGrossWeight(Double.valueOf(grossWeight));
                    sw_in.setVolume(Double.valueOf(volume));
                    sw_in.setLotCode(targetLotCode);
                    sw_in.setLastStockWorkUuid("");
                    sw_in.setControlWord(StringUtil.fixKey("", 20, "0"));
                    sw_in.setOfficeCode(lt.getOfficeCode());
                    sw_in.setLastStockWorkUuid("");
                    if(StringUtil.isNull(new_locTaskUuid)) {
                        sw_in = (StockWorkModel)this.dao.save(sw_in);
                        sw_in.setInStockWorkUuid(sw_in.getStockWorkUuid());
                    }

                    lt.setInStockWorkUuid(sw_in.getStockWorkUuid());
                    lt = (LocationTaskModel)this.dao.save(lt);
                    sw_in = this.setInRemainSinwork(lt, sw_in);
                    this.setInRemainHoldUuid(lt, sw_in);
                    new LogisticsOrderDetailModel();
                    List l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).query();
                    if(l2 == null || l2.size() <= 0) {
                        throw new ApplicationException("加工撤单--无法检索到源材料logisticsOrderDetailUuid");
                    }

                    LogisticsOrderDetailModel lod = (LogisticsOrderDetailModel)l2.get(0);
                    lod.setDeliveredQty(Double.valueOf(StringUtil.doubleTo0(lod.getDeliveredQty()) - StringUtil.doubleTo0(sw_in.getQty())));
                    lod.setDeliveredQty(StringUtil.ObjectToDouble(lod.getDeliveredQty(), 6));
                    if(StringUtil.doubleTo0(lod.getConfirmedQty()) > 0.0D) {
                        lod.setConfirmedQty(Double.valueOf(StringUtil.doubleTo0(lod.getConfirmedQty()) - StringUtil.doubleTo0(sw_in.getQty())));
                        lod.setConfirmedQty(StringUtil.ObjectToDouble(lod.getConfirmedQty(), 6));
                    }

                    lod = (LogisticsOrderDetailModel)this.dao.save(lod);
                    String loUuid = lod.getLogisticsOrderUuid();
                    if(StringUtil.isNull(loUuid)) {
                        throw new ApplicationException("加工撤单--loUuid为空");
                    }

                    LogisticsOrderModel lo = (LogisticsOrderModel)this.dao.get(LogisticsOrderModel.class, loUuid);
                    if(lo.getCutOffDate() != null) {
                        lo.setCutOffDate((Date)null);
                        lo = (LogisticsOrderModel)this.dao.save(lo);
                    }
                }

                return lt;
            }
        }
    }

    private LocationTaskModel pproOpt(LocationTaskModel lt) {
        if(StringUtil.isNull(lt.getControlWord())) {
            throw new ApplicationException("取消加工核销--ControlWord为空");
        } else if(!lt.getControlWord().substring(0, 1).equals("S") && !lt.getControlWord().substring(0, 1).equals("T")) {
            throw new ApplicationException("取消加工核销--ControlWord不符合要求");
        } else {
            String ifTargetLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getTargetLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
            String ifTargetLotCode_flag = this.sqlQueryManager.getColumnData(ifTargetLotCodeSql, "", "");
            if(StringUtil.isNull(ifTargetLotCode_flag)) {
                throw new ApplicationException("取消加工核销--当前货位[" + lt.getTargetLotCode() + "]不正确");
            } else {
                String locTaskUuid = lt.getLocationTaskUuid();
                double qty = StringUtil.doubleTo0(lt.getQty());
                double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                double volume = StringUtil.doubleTo0(lt.getVolume());
                String targetLotCode = lt.getTargetLotCode();
                String inStockWorkUuid = lt.getInStockWorkUuid();
                StockWorkModel sw_in;
                LogisticsOrderDetailModel lod;
                List l2;
                if(lt.getControlWord().substring(0, 1).equals("S")) {
                    if(StringUtil.isNull(lt.getInStockWorkUuid())) {
                        throw new ApplicationException("取消加工核销--源货物inStockWorkUuid为空");
                    }

                    sw_in = new StockWorkModel();
                    sw_in.setLocationTaskUuid(locTaskUuid);
                    sw_in.setInStockWorkUuid(inStockWorkUuid);
                    sw_in.setStockDate(this.sqlQueryManager.getSysDate(""));
                    sw_in.setStockType("SOT");
                    sw_in.setStockDesc("取消加工核销");
                    sw_in.setQty(Double.valueOf(qty));
                    sw_in.setSecondQty(Double.valueOf(secondQty));
                    sw_in.setThirdQty(Double.valueOf(thirdQty));
                    sw_in.setNetWeight(Double.valueOf(netWeight));
                    sw_in.setGrossWeight(Double.valueOf(grossWeight));
                    sw_in.setVolume(Double.valueOf(volume));
                    sw_in.setLotCode(targetLotCode);
                    sw_in.setLastStockWorkUuid("");
                    sw_in.setControlWord(StringUtil.fixKey("", 20, "0"));
                    sw_in.setOfficeCode(lt.getOfficeCode());
                    lt = this.setOutRemainSinwork(lt, sw_in);
                    sw_in.setNetWeight(lt.getNetWeight());
                    sw_in.setGrossWeight(lt.getGrossWeight());
                    sw_in.setVolume(lt.getVolume());
                    this.setOutRemainHold(lt, sw_in);
                    lt = (LocationTaskModel)this.dao.save(lt);
                    new LogisticsOrderDetailModel();
                    l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).query();
                    if(l2 == null || l2.size() <= 0) {
                        throw new ApplicationException("取消加工核销--无法检索到成品logisticsOrderDetailUuid");
                    }

                    lod = (LogisticsOrderDetailModel)l2.get(0);
                    lod.setConfirmedQty(Double.valueOf(StringUtil.doubleTo0(lod.getConfirmedQty()) - StringUtil.doubleTo0(sw_in.getQty())));
                    lod.setConfirmedQty(StringUtil.ObjectToDouble(lod.getConfirmedQty(), 6));
                    lod = (LogisticsOrderDetailModel)this.dao.save(lod);
                } else if(lt.getControlWord().substring(0, 1).equals("T")) {
                    sw_in = new StockWorkModel();
                    sw_in.setLocationTaskUuid(locTaskUuid);
                    sw_in.setInStockWorkUuid(inStockWorkUuid);
                    sw_in.setStockDate(this.sqlQueryManager.getSysDate(""));
                    sw_in.setStockType("SIN");
                    sw_in.setStockDesc("取消加工核销");
                    sw_in.setQty(Double.valueOf(qty));
                    sw_in.setSecondQty(Double.valueOf(secondQty));
                    sw_in.setThirdQty(Double.valueOf(thirdQty));
                    sw_in.setNetWeight(Double.valueOf(netWeight));
                    sw_in.setGrossWeight(Double.valueOf(grossWeight));
                    sw_in.setVolume(Double.valueOf(volume));
                    sw_in.setLotCode(targetLotCode);
                    sw_in.setLastStockWorkUuid("");
                    sw_in.setControlWord(StringUtil.fixKey("", 20, "0"));
                    sw_in.setOfficeCode(lt.getOfficeCode());
                    sw_in.setLastStockWorkUuid("");
                    sw_in = (StockWorkModel)this.dao.save(sw_in);
                    sw_in.setInStockWorkUuid(sw_in.getStockWorkUuid());
                    lt.setInStockWorkUuid(sw_in.getStockWorkUuid());
                    lt = (LocationTaskModel)this.dao.save(lt);
                    sw_in = this.setInRemainSinwork(lt, sw_in);
                    this.setInRemainHoldUuid(lt, sw_in);
                    new LogisticsOrderDetailModel();
                    l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).query();
                    if(l2 == null || l2.size() <= 0) {
                        throw new ApplicationException("取消加工核销--无法检索到源材料logisticsOrderDetailUuid");
                    }

                    lod = (LogisticsOrderDetailModel)l2.get(0);
                    lod.setDeliveredQty(Double.valueOf(StringUtil.doubleTo0(lod.getDeliveredQty()) - StringUtil.doubleTo0(sw_in.getQty())));
                    lod.setDeliveredQty(StringUtil.ObjectToDouble(lod.getDeliveredQty(), 6));
                    lod = (LogisticsOrderDetailModel)this.dao.save(lod);
                    String loUuid = lod.getLogisticsOrderUuid();
                    if(StringUtil.isNull(loUuid)) {
                        throw new ApplicationException("取消加工核销--loUuid为空");
                    }

                    if(this.getLodDeliveredQty(loUuid, "PDN") <= 0.0D) {
                        LogisticsOrderModel new_lt = (LogisticsOrderModel)this.dao.get(LogisticsOrderModel.class, loUuid);
                        if(new_lt.getCutOffDate() != null) {
                            new_lt.setCutOffDate((Date)null);
                            new_lt = (LogisticsOrderModel)this.dao.save(new_lt);
                        }
                    }

                    LocationTaskModel new_lt1 = new LocationTaskModel();

                    try {
                        RcUtil.copyProperties(new_lt1, lt);
                    } catch (Exception var26) {
                        var26.printStackTrace();
                    }

                    new_lt1.setLocationTaskUuid((String)null);
                    new_lt1.setLocTaskNo(this.wmsCommonManager.generateNumberByRule("SEQUENCE_LT", lt.getOfficeCode()));
                    new_lt1.setLocTaskType("PICK");
                    new_lt1.setLastLocationTaskUuid(locTaskUuid);
                    if(StringUtil.isNull(new_lt1.getSourceLotCode())) {
                        new_lt1.setSourceLotCode(new_lt1.getTargetLotCode());
                    }

                    new_lt1 = (LocationTaskModel)this.dao.save(new_lt1);
                    new LogisticsOrderDetailModel();
                    List new_lod_list = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", new_lt1.getLogisticsOrderDetailUuid())).addCondition(Condition.or(new Condition[]{Condition.eq("transactionType", "SOT"), Condition.and(new Condition[]{Condition.eq("transactionType", "PDN"), Condition.eq("substr(controlWord, 1, 1)", "S")})})).query();
                    if(new_lod_list == null || new_lod_list.size() <= 0) {
                        throw new ApplicationException("取消加工核销--无法检索到扣减的logisticsOrderDetailUuid");
                    }

                    LogisticsOrderDetailModel new_lod = (LogisticsOrderDetailModel)new_lod_list.get(0);
                    new_lod.setConfirmedQty(Double.valueOf(StringUtil.doubleTo0(new_lod.getConfirmedQty()) - StringUtil.doubleTo0(new_lt1.getQty())));
                    new_lod.setConfirmedQty(StringUtil.ObjectToDouble(new_lod.getConfirmedQty(), 6));
                    new_lod = (LogisticsOrderDetailModel)this.dao.save(new_lod);
                    this.pickOpt(new_lt1);
                }

                return lt;
            }
        }
    }

    private LocationTaskModel unpaOpt(LocationTaskModel lt) {
        if(StringUtil.isNull(lt.getControlWord())) {
            throw new ApplicationException("货物拆分 -- ControlWord为空");
        } else if(!lt.getControlWord().substring(0, 1).equals("S") && !lt.getControlWord().substring(0, 1).equals("T")) {
            throw new ApplicationException("货物拆分 -- ControlWord不符合要求");
        } else {
            String ifTargetLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getTargetLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
            String ifTargetLotCode_flag = this.sqlQueryManager.getColumnData(ifTargetLotCodeSql, "", "");
            if(StringUtil.isNull(ifTargetLotCode_flag)) {
                throw new ApplicationException("货物拆分 -- 当前货位[" + lt.getTargetLotCode() + "]不正确");
            } else {
                String locTaskUuid = lt.getLocationTaskUuid();
                double qty = StringUtil.doubleTo0(lt.getQty());
                double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                double volume = StringUtil.doubleTo0(lt.getVolume());
                String targetLotCode = lt.getTargetLotCode();
                String inStockWorkUuid = lt.getInStockWorkUuid();
                if(lt.getControlWord().substring(0, 1).equals("S")) {
                    if(StringUtil.isNull(lt.getInStockWorkUuid())) {
                        throw new ApplicationException("货物拆分 -- 源货物inStockWorkUuid为空");
                    }

                    String sw_in = this.checkRemainHoldQty(lt);
                    if(StringUtil.isNull(sw_in)) {
                        throw new ApplicationException("货物拆分 -- 已办理出库作业单,库存的可出库不够,无法进行货物拆分");
                    }

                    StockWorkModel sw_out = new StockWorkModel();
                    sw_out.setLocationTaskUuid(locTaskUuid);
                    sw_out.setInStockWorkUuid(inStockWorkUuid);
                    sw_out.setStockDate(this.sqlQueryManager.getSysDate(""));
                    sw_out.setStockType("SOT");
                    sw_out.setStockDesc("货物拆分");
                    sw_out.setQty(Double.valueOf(qty));
                    sw_out.setSecondQty(Double.valueOf(secondQty));
                    sw_out.setThirdQty(Double.valueOf(thirdQty));
                    sw_out.setNetWeight(Double.valueOf(netWeight));
                    sw_out.setGrossWeight(Double.valueOf(grossWeight));
                    sw_out.setVolume(Double.valueOf(volume));
                    sw_out.setLotCode(targetLotCode);
                    sw_out.setLastStockWorkUuid("");
                    sw_out.setControlWord(StringUtil.fixKey("", 20, "0"));
                    sw_out.setOfficeCode(lt.getOfficeCode());
                    if(StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                        lt.setLastLocationTaskUuid(this.getLastLocationTaskUuid(sw_out.getInStockWorkUuid()));
                        lt = (LocationTaskModel)this.dao.save(lt);
                    }

                    if(!StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                        new LocationTaskModel();
                        List ls_lt = this.dao.createCommonQuery(LocationTaskModel.class).addCondition(Condition.eq("locationTaskUuid", lt.getLastLocationTaskUuid())).query();
                        if(ls_lt != null && ls_lt.size() > 0) {
                            LocationTaskModel ltm = (LocationTaskModel)ls_lt.get(0);
                            if(ltm.getLocTaskType() != null && ltm.getLocTaskType().equals("PICK")) {
                                throw new ApplicationException("货物拆分 -- 当前记录为拣货记录，不允许操作");
                            }
                        }
                    }

                    lt = this.setOutRemainSinwork(lt, sw_out);
                    sw_out.setNetWeight(lt.getNetWeight());
                    sw_out.setGrossWeight(lt.getGrossWeight());
                    sw_out.setVolume(lt.getVolume());
                    this.setOutRemainHold(lt, sw_out);
                    lt = (LocationTaskModel)this.dao.save(lt);
                } else if(lt.getControlWord().substring(0, 1).equals("T")) {
                    StockWorkModel sw_in1 = new StockWorkModel();
                    sw_in1.setLocationTaskUuid(locTaskUuid);
                    sw_in1.setInStockWorkUuid(inStockWorkUuid);
                    sw_in1.setStockDate(this.sqlQueryManager.getSysDate(""));
                    sw_in1.setStockType("SIN");
                    sw_in1.setStockDesc("货物拆分");
                    sw_in1.setQty(Double.valueOf(qty));
                    sw_in1.setSecondQty(Double.valueOf(secondQty));
                    sw_in1.setThirdQty(Double.valueOf(thirdQty));
                    sw_in1.setNetWeight(Double.valueOf(netWeight));
                    sw_in1.setGrossWeight(Double.valueOf(grossWeight));
                    sw_in1.setVolume(Double.valueOf(volume));
                    sw_in1.setLotCode(targetLotCode);
                    sw_in1.setLastStockWorkUuid("");
                    sw_in1.setControlWord(StringUtil.fixKey("", 20, "0"));
                    sw_in1.setOfficeCode(lt.getOfficeCode());
                    sw_in1.setLastStockWorkUuid("");
                    sw_in1 = (StockWorkModel)this.dao.save(sw_in1);
                    sw_in1.setInStockWorkUuid(sw_in1.getStockWorkUuid());
                    lt.setInStockWorkUuid(sw_in1.getStockWorkUuid());
                    lt = (LocationTaskModel)this.dao.save(lt);
                    sw_in1 = this.setInRemainSinwork(lt, sw_in1);
                    this.setInRemainHold(lt, sw_in1);
                }

                return lt;
            }
        }
    }

    private LocationTaskModel cnpaOpt(LocationTaskModel lt) {
        if(StringUtil.isNull(lt.getControlWord())) {
            throw new ApplicationException("取消货物拆分 -- ControlWord为空");
        } else if(!lt.getControlWord().substring(0, 1).equals("S") && !lt.getControlWord().substring(0, 1).equals("T")) {
            throw new ApplicationException("取消货物拆分 -- ControlWord不符合要求");
        } else {
            String ifTargetLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getTargetLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
            String ifTargetLotCode_flag = this.sqlQueryManager.getColumnData(ifTargetLotCodeSql, "", "");
            if(StringUtil.isNull(ifTargetLotCode_flag)) {
                throw new ApplicationException("取消货物拆分 -- 当前货位[" + lt.getTargetLotCode() + "]不正确");
            } else {
                String locTaskUuid = lt.getLocationTaskUuid();
                double qty = StringUtil.doubleTo0(lt.getQty());
                double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                double volume = StringUtil.doubleTo0(lt.getVolume());
                String targetLotCode = lt.getTargetLotCode();
                String inStockWorkUuid = lt.getInStockWorkUuid();
                StockWorkModel sw_in;
                if(lt.getControlWord().substring(0, 1).equals("S")) {
                    if(StringUtil.isNull(lt.getInStockWorkUuid())) {
                        throw new ApplicationException("取消货物拆分--源货物inStockWorkUuid为空");
                    }

                    sw_in = new StockWorkModel();
                    sw_in.setLocationTaskUuid(locTaskUuid);
                    sw_in.setInStockWorkUuid(inStockWorkUuid);
                    sw_in.setStockDate(this.sqlQueryManager.getSysDate(""));
                    sw_in.setStockType("SOT");
                    sw_in.setStockDesc("取消货物拆分");
                    sw_in.setQty(Double.valueOf(qty));
                    sw_in.setSecondQty(Double.valueOf(secondQty));
                    sw_in.setThirdQty(Double.valueOf(thirdQty));
                    sw_in.setNetWeight(Double.valueOf(netWeight));
                    sw_in.setGrossWeight(Double.valueOf(grossWeight));
                    sw_in.setVolume(Double.valueOf(volume));
                    sw_in.setLotCode(targetLotCode);
                    sw_in.setLastStockWorkUuid("");
                    sw_in.setControlWord(StringUtil.fixKey("", 20, "0"));
                    sw_in.setOfficeCode(lt.getOfficeCode());
                    lt = this.setOutRemainSinwork(lt, sw_in);
                    sw_in.setNetWeight(lt.getNetWeight());
                    sw_in.setGrossWeight(lt.getGrossWeight());
                    sw_in.setVolume(lt.getVolume());
                    this.setOutRemainHold(lt, sw_in);
                    lt = (LocationTaskModel)this.dao.save(lt);
                    if(!StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                        new LocationTaskModel();
                        List ls_lt = this.dao.createCommonQuery(LocationTaskModel.class).addCondition(Condition.eq("locationTaskUuid", lt.getLastLocationTaskUuid())).query();
                        if(ls_lt != null && ls_lt.size() > 0) {
                            LocationTaskModel ltm = (LocationTaskModel)ls_lt.get(0);
                            if(ltm.getLocTaskType() != null && ltm.getLocTaskType().equals("PICK")) {
                                throw new ApplicationException("取消货物拆分 -- 当前记录为拣货记录，不允许操作");
                            }
                        }
                    }
                } else if(lt.getControlWord().substring(0, 1).equals("T")) {
                    if(!StringUtil.isNull(lt.getBarcode())) {
                        RemainSinworkModel sw_in1 = this.getInsRemainSinworkUuid(lt);
                        if(!StringUtil.isNull(sw_in1.getInStockWorkUuid()) && !StringUtil.isNull(sw_in1.getLotCode())) {
                            inStockWorkUuid = lt.getInStockWorkUuid();
                            targetLotCode = lt.getTargetLotCode();
                        }
                    }

                    sw_in = new StockWorkModel();
                    sw_in.setLocationTaskUuid(locTaskUuid);
                    sw_in.setInStockWorkUuid(inStockWorkUuid);
                    sw_in.setStockDate(this.sqlQueryManager.getSysDate(""));
                    sw_in.setStockType("SIN");
                    sw_in.setStockDesc("取消货物拆分");
                    sw_in.setQty(Double.valueOf(qty));
                    sw_in.setSecondQty(Double.valueOf(secondQty));
                    sw_in.setThirdQty(Double.valueOf(thirdQty));
                    sw_in.setNetWeight(Double.valueOf(netWeight));
                    sw_in.setGrossWeight(Double.valueOf(grossWeight));
                    sw_in.setVolume(Double.valueOf(volume));
                    sw_in.setLotCode(targetLotCode);
                    sw_in.setLastStockWorkUuid("");
                    sw_in.setControlWord(StringUtil.fixKey("", 20, "0"));
                    sw_in.setOfficeCode(lt.getOfficeCode());
                    sw_in.setLastStockWorkUuid("");
                    sw_in = (StockWorkModel)this.dao.save(sw_in);
                    lt.setInStockWorkUuid(sw_in.getStockWorkUuid());
                    lt = (LocationTaskModel)this.dao.save(lt);
                    sw_in = this.setInRemainSinwork(lt, sw_in);
                    this.setInRemainHold(lt, sw_in);
                }

                return lt;
            }
        }
    }

    private LocationTaskModel natuOpt(LocationTaskModel lt) {
        if(StringUtil.isNull(lt.getSourceLotCode())) {
            throw new ApplicationException("货物属性修改 -- 源货位为空");
        } else if(StringUtil.isNull(lt.getTargetLotCode())) {
            throw new ApplicationException("货物属性修改 -- 目的货位为空");
        } else if(StringUtil.isNull(lt.getInStockWorkUuid())) {
            throw new ApplicationException("货物属性修改 -- InStockWorkUuid为空");
        } else {
            String ifHoldQtyFlag = this.checkRemainHoldQty(lt);
            if(StringUtil.isNull(ifHoldQtyFlag)) {
                throw new ApplicationException("货物属性修改 -- 当前的库存货物明细已办理出库单，不能修改货物属性");
            } else {
                String ifTargetLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getTargetLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
                String ifTargetLotCode_flag = this.sqlQueryManager.getColumnData(ifTargetLotCodeSql, "", "");
                if(StringUtil.isNull(ifTargetLotCode_flag)) {
                    throw new ApplicationException("货物属性修改 -- 当前货位[" + lt.getTargetLotCode() + "]不正确");
                } else {
                    if(StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                        lt.setLastLocationTaskUuid(this.getLastLocationTaskUuid(lt.getInStockWorkUuid()));
                    }

                    if(!StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                        new LocationTaskModel();
                        List qty = this.dao.createCommonQuery(LocationTaskModel.class).addCondition(Condition.eq("locationTaskUuid", lt.getLastLocationTaskUuid())).query();
                        if(qty != null && qty.size() > 0) {
                            LocationTaskModel locTaskUuid = (LocationTaskModel)qty.get(0);
                            if(locTaskUuid.getLocTaskType() != null && locTaskUuid.getLocTaskType().equals("PICK")) {
                                throw new ApplicationException("货物属性修改 -- 当前记录为拣货记录，不允许操作");
                            }
                        }
                    }

                    String locTaskUuid1 = lt.getLocationTaskUuid();
                    double qty1 = StringUtil.doubleTo0(lt.getQty());
                    double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                    double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                    String targetLotCode = lt.getTargetLotCode();
                    String inStockWorkUuid = lt.getInStockWorkUuid();
                    StockWorkModel sw_out = new StockWorkModel();
                    LocationTaskModel lt_out = new LocationTaskModel();
                    new RemainSinworkModel();
                    List ls_rs = this.dao.createCommonQuery(RemainSinworkModel.class).addCondition(Condition.eq("inStockWorkUuid", inStockWorkUuid)).addCondition(Condition.eq("lotCode", lt.getSourceLotCode())).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
                    if(ls_rs != null && ls_rs.size() > 0) {
                        RemainSinworkModel rs = (RemainSinworkModel)ls_rs.get(0);
                        sw_out.setInStockWorkUuid(inStockWorkUuid);
                        sw_out.setLotCode(lt.getSourceLotCode());
                        sw_out.setQty(rs.getRemainQty());
                        sw_out.setGrossWeight(rs.getRemainGrossWeight());
                        sw_out.setNetWeight(rs.getRemainNetWeight());
                        sw_out.setVolume(rs.getRemainVolume());
                        sw_out.setSecondQty(rs.getRemainSecondQty());
                        sw_out.setOfficeCode(rs.getOfficeCode());
                        lt_out.setLocTaskType(lt.getLocTaskType());
                        lt_out.setInLogisticsOrderDetailUuid(rs.getInLogisticsOrderDetailUuid());
                        lt_out.setInStockWorkUuid(inStockWorkUuid);
                        lt_out.setBatchNo(rs.getBatchNo());
                        lt_out.setItemCode(rs.getItemCode());
                        lt_out.setMarksNumber(rs.getMarksNumber());
                        lt_out.setGoodsNature(rs.getGoodsNature());
                        lt_out.setGoodsKind(rs.getGoodsKind());
                        lt_out.setUnitCode(rs.getInstockUnitCode());
                        lt_out.setTargetLotCode(lt.getSourceLotCode());
                        lt_out.setQty(rs.getRemainQty());
                        lt_out.setGrossWeight(rs.getRemainGrossWeight());
                        lt_out.setNetWeight(rs.getRemainNetWeight());
                        lt_out.setVolume(rs.getRemainVolume());
                        lt_out.setSecondQty(rs.getRemainSecondQty());
                        lt_out.setProductionDate(rs.getProductionDate());
                        lt_out.setOfficeCode(rs.getOfficeCode());
                        lt_out.setPackageNo(rs.getPackageNo());
                        lt_out = this.setOutRemainSinwork(lt_out, sw_out);
                        sw_out.setNetWeight(lt_out.getNetWeight());
                        sw_out.setGrossWeight(lt_out.getGrossWeight());
                        sw_out.setVolume(lt_out.getVolume());
                        this.setOutRemainHold(lt_out, sw_out);
                        StockWorkModel sw_in = new StockWorkModel();
                        sw_in.setLocationTaskUuid(locTaskUuid1);
                        sw_in.setInStockWorkUuid(inStockWorkUuid);
                        sw_in.setStockDate(this.sqlQueryManager.getSysDate(""));
                        sw_in.setStockType("SIN");
                        sw_in.setStockDesc("货物属性修改");
                        sw_in.setQty(Double.valueOf(qty1));
                        sw_in.setSecondQty(Double.valueOf(secondQty));
                        sw_in.setThirdQty(Double.valueOf(thirdQty));
                        sw_in.setNetWeight(lt.getNetWeight());
                        sw_in.setGrossWeight(lt.getGrossWeight());
                        sw_in.setVolume(lt.getVolume());
                        sw_in.setLotCode(targetLotCode);
                        sw_in.setLastStockWorkUuid("");
                        sw_in.setControlWord(StringUtil.fixKey("", 20, "0"));
                        sw_in.setOfficeCode(lt.getOfficeCode());
                        sw_in.setLastStockWorkUuid("");
                        sw_in = (StockWorkModel)this.dao.save(sw_in);
                        sw_in.setInStockWorkUuid(sw_in.getStockWorkUuid());
                        lt.setInStockWorkUuid(sw_in.getStockWorkUuid());
                        lt = (LocationTaskModel)this.dao.save(lt);
                        sw_in = this.setInRemainSinwork(lt, sw_in);
                        this.setInRemainHold(lt, sw_in);
                        return lt;
                    } else {
                        throw new ApplicationException("货物属性修改 -- 没有检索到库存货物");
                    }
                }
            }
        }
    }

    private LocationTaskModel workOpt(LocationTaskModel lt) {
        if(StringUtil.isNull(lt.getBarcode())) {
            throw new ApplicationException("库内作业 -- 条码为空");
        } else if(lt.getQty() != null && lt.getQty().doubleValue() >= 0.0D) {
            if(StringUtil.isNull(lt.getInStockWorkUuid())) {
                throw new ApplicationException("库内作业 -- InStockWorkUuid为空");
            } else {
                this.checkinStockWorkUuid(lt);
                new RemainSinworkModel();
                List l = this.dao.createCommonQuery(RemainSinworkModel.class).addCondition(Condition.eq("inStockWorkUuid", lt.getInStockWorkUuid())).addCondition(Condition.eq("barcode", lt.getBarcode())).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
                if(l != null && l.size() > 0) {
                    RemainSinworkModel rs = (RemainSinworkModel)l.get(0);
                    double remainQty = StringUtil.doubleTo0(rs.getRemainQty());
                    double remainNetWeight = StringUtil.doubleTo0(rs.getRemainNetWeight());
                    double remainGrossWeight = StringUtil.doubleTo0(rs.getRemainGrossWeight());
                    double remainVolume = StringUtil.doubleTo0(rs.getRemainVolume());
                    double ltQty = StringUtil.doubleTo0(lt.getQty());
                    double ltNetWeight = StringUtil.doubleTo0(lt.getNetWeight());
                    double ltGrossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                    double ltVolume = StringUtil.doubleTo0(lt.getVolume());
                    double ltWidth = StringUtil.doubleTo0(lt.getWidth());
                    double ltLength = StringUtil.doubleTo0(lt.getLength());
                    double ltHeight = StringUtil.doubleTo0(lt.getHeight());
                    double cyQty = StringUtil.doubleTo0(Double.valueOf(remainQty - ltQty));
                    double cyNetWeight = StringUtil.doubleTo0(Double.valueOf(remainNetWeight - ltNetWeight));
                    double cyGrossWeight = StringUtil.doubleTo0(Double.valueOf(remainGrossWeight - ltGrossWeight));
                    double cyVolume = StringUtil.doubleTo0(Double.valueOf(remainVolume - ltVolume));
                    String rhUuid = this.getRStoRemainHold(rs);
                    if(!StringUtil.isNull(rhUuid)) {
                        List rhList = this.dao.createCommonQuery(RemainHoldModel.class).addCondition(Condition.eq("remainHoldUuid", rhUuid)).addCondition(Condition.eq("officeCode", rs.getOfficeCode())).query();
                        if(rhList != null && rhList.size() > 0) {
                            RemainHoldModel rh = (RemainHoldModel)rhList.get(0);
                            if(StringUtil.doubleTo0(rh.getRemainQty()) + cyQty < StringUtil.doubleTo0(rh.getHoldQty())) {
                                throw new ApplicationException("库内作业 -- 调整的库存数<可出库数");
                            } else {
                                rh.setRemainQty(Double.valueOf(StringUtil.doubleTo0(rh.getRemainQty()) - cyQty));
                                rh.setRemainNetWeight(Double.valueOf(StringUtil.doubleTo0(rh.getRemainNetWeight()) - cyNetWeight));
                                rh.setRemainGrossWeight(Double.valueOf(StringUtil.doubleTo0(rh.getRemainGrossWeight()) - cyGrossWeight));
                                rh.setRemainVolume(Double.valueOf(StringUtil.doubleTo0(rh.getRemainVolume()) - cyVolume));
                                rs.setRemainQty(Double.valueOf(ltQty));
                                rs.setRemainGrossWeight(Double.valueOf(ltGrossWeight));
                                rs.setRemainNetWeight(Double.valueOf(ltNetWeight));
                                rs.setRemainVolume(Double.valueOf(ltVolume));
                                rs.setWidth(Double.valueOf(ltWidth));
                                rs.setLength(Double.valueOf(ltLength));
                                rs.setHeight(Double.valueOf(ltHeight));
                                rs = (RemainSinworkModel)this.dao.save(rs);
                                rh = (RemainHoldModel)this.dao.save(rh);
                                return lt;
                            }
                        } else {
                            throw new ApplicationException("库内作业 -- 没有检索RH记录");
                        }
                    } else {
                        throw new ApplicationException("库内作业 -- 没有检索可出库记录");
                    }
                } else {
                    throw new ApplicationException("库内作业 -- 没有检索到库存记录");
                }
            }
        } else {
            throw new ApplicationException("库内作业 -- 数量为空");
        }
    }

    private LocationTaskModel dsocOpt(LocationTaskModel lt) {
        if(StringUtil.isNull(lt.getSourceLotCode())) {
            throw new ApplicationException("送检核销 -- 源货位为空");
        } else if(StringUtil.isNull(lt.getTargetLotCode())) {
            throw new ApplicationException("送检核销 -- 目的货位为空");
        } else if(StringUtil.isNull(lt.getInStockWorkUuid())) {
            throw new ApplicationException("送检核销 -- InStockWorkUuid为空");
        } else {
            String ifTargetLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getTargetLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
            String ifTargetLotCode_flag = this.sqlQueryManager.getColumnData(ifTargetLotCodeSql, "", "");
            if(StringUtil.isNull(ifTargetLotCode_flag)) {
                throw new ApplicationException("送检核销 -- 当前货位[" + lt.getTargetLotCode() + "]不正确");
            } else {
                if(StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                    lt.setLastLocationTaskUuid(this.getLastLocationTaskUuid(lt.getInStockWorkUuid()));
                }

                String locTaskUuid = lt.getLocationTaskUuid();
                double qty = StringUtil.doubleTo0(lt.getQty());
                double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                double volume = StringUtil.doubleTo0(lt.getVolume());
                String targetLotCode = lt.getTargetLotCode();
                String inStockWorkUuid = lt.getInStockWorkUuid();
                StockWorkModel sw_out = new StockWorkModel();
                LocationTaskModel lt_out = new LocationTaskModel();
                new RemainSinworkModel();
                List l = this.dao.createCommonQuery(RemainSinworkModel.class).addCondition(Condition.eq("inStockWorkUuid", inStockWorkUuid)).addCondition(Condition.eq("lotCode", lt.getSourceLotCode())).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
                if(l != null && l.size() > 0) {
                    RemainSinworkModel rs = (RemainSinworkModel)l.get(0);
                    sw_out.setInStockWorkUuid(inStockWorkUuid);
                    sw_out.setLotCode(lt.getSourceLotCode());
                    sw_out.setQty(rs.getRemainQty());
                    sw_out.setGrossWeight(rs.getRemainGrossWeight());
                    sw_out.setNetWeight(rs.getRemainNetWeight());
                    sw_out.setVolume(rs.getRemainVolume());
                    sw_out.setSecondQty(rs.getRemainSecondQty());
                    sw_out.setOfficeCode(rs.getOfficeCode());
                    lt_out.setLocTaskType(lt.getLocTaskType());
                    lt_out.setLogisticsOrderDetailUuid(lt.getLogisticsOrderDetailUuid());
                    lt_out.setInLogisticsOrderDetailUuid(rs.getInLogisticsOrderDetailUuid());
                    lt_out.setInStockWorkUuid(inStockWorkUuid);
                    lt_out.setBatchNo(rs.getBatchNo());
                    lt_out.setItemCode(rs.getItemCode());
                    lt_out.setMarksNumber(rs.getMarksNumber());
                    lt_out.setGoodsNature(rs.getGoodsNature());
                    lt_out.setGoodsKind(rs.getGoodsKind());
                    lt_out.setUnitCode(rs.getInstockUnitCode());
                    lt_out.setTargetLotCode(lt.getSourceLotCode());
                    lt_out.setQty(rs.getRemainQty());
                    lt_out.setGrossWeight(rs.getRemainGrossWeight());
                    lt_out.setNetWeight(rs.getRemainNetWeight());
                    lt_out.setVolume(rs.getRemainVolume());
                    lt_out.setSecondQty(rs.getRemainSecondQty());
                    lt_out.setProductionDate(rs.getProductionDate());
                    lt_out.setOfficeCode(rs.getOfficeCode());
                    lt_out.setPackageNo(rs.getPackageNo());
                    lt_out = this.setOutRemainSinwork(lt_out, sw_out);
                    lt.setGrossWeight(lt_out.getGrossWeight());
                    lt.setNetWeight(lt_out.getNetWeight());
                    lt.setVolume(lt_out.getVolume());
                    sw_out.setNetWeight(lt_out.getNetWeight());
                    sw_out.setGrossWeight(lt_out.getGrossWeight());
                    sw_out.setVolume(lt_out.getVolume());
                    netWeight = lt_out.getNetWeight().doubleValue();
                    grossWeight = lt_out.getGrossWeight().doubleValue();
                    volume = lt_out.getVolume().doubleValue();
                    this.setOutRemainHoldUuid(lt_out, sw_out);
                    StockWorkModel sw_in = new StockWorkModel();
                    sw_in.setLocationTaskUuid(locTaskUuid);
                    sw_in.setInStockWorkUuid(inStockWorkUuid);
                    sw_in.setStockDate(this.sqlQueryManager.getSysDate(""));
                    sw_in.setStockType("SIN");
                    sw_in.setStockDesc("送检核销");
                    sw_in.setQty(Double.valueOf(qty));
                    sw_in.setSecondQty(Double.valueOf(secondQty));
                    sw_in.setThirdQty(Double.valueOf(thirdQty));
                    sw_in.setNetWeight(lt.getNetWeight());
                    sw_in.setGrossWeight(lt.getGrossWeight());
                    sw_in.setVolume(lt.getVolume());
                    sw_in.setLotCode(targetLotCode);
                    sw_in.setLastStockWorkUuid("");
                    sw_in.setControlWord(StringUtil.fixKey("", 20, "0"));
                    sw_in.setOfficeCode(lt.getOfficeCode());
                    sw_in.setLastStockWorkUuid("");
                    sw_in = (StockWorkModel)this.dao.save(sw_in);
                    sw_in.setInStockWorkUuid(sw_in.getStockWorkUuid());
                    lt.setInStockWorkUuid(sw_in.getStockWorkUuid());
                    lt = (LocationTaskModel)this.dao.save(lt);
                    sw_in = this.setInRemainSinwork(lt, sw_in);
                    this.setInRemainHold(lt, sw_in);
                    new LogisticsOrderDetailModel();
                    List l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).addCondition(Condition.eq("transactionType", "SOC")).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
                    if(l2 != null && l2.size() > 0) {
                        LogisticsOrderDetailModel lod = (LogisticsOrderDetailModel)l2.get(0);
                        lod.setDeliveredQty(Double.valueOf(StringUtil.doubleTo0(lod.getDeliveredQty()) + StringUtil.doubleTo0(sw_in.getQty())));
                        lod.setDeliveredQty(StringUtil.ObjectToDouble(lod.getDeliveredQty(), 6));
                        if(lod.getDeliveredQty().doubleValue() > lod.getQty().doubleValue() + 1.0E-6D) {
                            throw new ApplicationException("送检核销--送检核销数量大于计划出库数");
                        } else {
                            lod = (LogisticsOrderDetailModel)this.dao.save(lod);
                            String loUuid = lod.getLogisticsOrderUuid();
                            if(!StringUtil.isNull(loUuid)) {
                                LogisticsOrderModel lo = (LogisticsOrderModel)this.dao.get(LogisticsOrderModel.class, loUuid);
                                if(lo.getCutOffDate() == null) {
                                    lo.setCutOffDate(lt.getLocTaskDate());
                                    lo = (LogisticsOrderModel)this.dao.save(lo);
                                }

                                return lt;
                            } else {
                                throw new ApplicationException("送检核销--loUuid为空");
                            }
                        }
                    } else {
                        throw new ApplicationException("出库核销操作--无法检索到logisticsOrderDetailUuid");
                    }
                } else {
                    throw new ApplicationException("送检核销 -- 没有检索到库存货物");
                }
            }
        }
    }

    private LocationTaskModel csocOpt(LocationTaskModel lt) {
        if(lt.getLogisticsOrderDetailUuid().equals(lt.getInLogisticsOrderDetailUuid())) {
            throw new ApplicationException("InLogisticsOrderDetailUuid和LogisticsOrderDetailUuid数据相同，无法操作");
        } else if(StringUtil.isNull(lt.getInStockWorkUuid())) {
            throw new ApplicationException("取消送检核销--InStockWorkUuid为空");
        } else if(StringUtil.isNull(lt.getLastLocationTaskUuid())) {
            throw new ApplicationException("取消送检核销--LastLocationTaskUuid为空");
        } else {
            String CW3_Sql = "select lod.logistics_order_no from logistics_order_detail lod,logistics_order lo   where lo.logistics_order_uuid=lod.logistics_order_uuid  and lod.logistics_order_detail_uuid=\'" + lt.getLogisticsOrderDetailUuid() + "\' " + " and ( substrb(nvl(lod.control_word,\'000\'),3,1)=\'F\' or " + " substrb(nvl(lo.control_word,\'000\'),3,1)=\'F\' )  and lo.transaction_type in (\'SOC\') ";
            String CW3_flag = this.sqlQueryManager.getColumnData(CW3_Sql, "", "");
            if(!StringUtil.isNull(CW3_flag)) {
                throw new ApplicationException("取消送检核销--[" + CW3_flag + "]单已完结，不允许执行取消送检核销操作");
            } else {
                new LocationTaskModel();
                new LocationTaskModel();
                String inStockWorkUuid = "";
                String locTaskUuid = "";
                String targetLotCode = "";
                String batchNo = "";
                String itemCode = "";
                String marksNumber = "";
                String goodsNature = "";
                String goodsKind = "";
                String unitCode = "";
                String itemSeqno = "";
                String extItemCode = "";
                String packageNo = "";
                String panelNo = "";
                List l_ltm = this.dao.createCommonQuery(LocationTaskModel.class).addCondition(Condition.eq("locationTaskUuid", lt.getLastLocationTaskUuid())).addCondition(Condition.eq("locTaskType", "DSOC")).query();
                if(l_ltm != null && l_ltm.size() > 0) {
                    LocationTaskModel ltm = (LocationTaskModel)l_ltm.get(0);
                    String qty = ltm.getLastLocationTaskUuid();
                    if(!StringUtil.isNull(qty)) {
                        List l_ltm2 = this.dao.createCommonQuery(LocationTaskModel.class).addCondition(Condition.eq("locationTaskUuid", qty)).query();
                        if(l_ltm2 != null && l_ltm2.size() > 0) {
                            LocationTaskModel ltm2 = (LocationTaskModel)l_ltm2.get(0);
                            inStockWorkUuid = ltm2.getInStockWorkUuid();
                            locTaskUuid = ltm2.getLocationTaskUuid();
                            targetLotCode = ltm2.getTargetLotCode();
                            batchNo = ltm2.getBatchNo();
                            itemCode = ltm2.getItemCode();
                            marksNumber = ltm2.getMarksNumber();
                            goodsNature = ltm2.getGoodsNature();
                            goodsKind = ltm2.getGoodsKind();
                            unitCode = ltm2.getUnitCode();
                            itemSeqno = ltm2.getItemSeqno();
                            extItemCode = ltm2.getExtItemCode();
                            packageNo = ltm2.getPackageNo();
                            panelNo = ltm2.getPanelNo();
                            if(StringUtil.isNull(inStockWorkUuid)) {
                                throw new ApplicationException("取消送检核销--核销前的InStockWorkUuid为空");
                            } else if(StringUtil.isNull(locTaskUuid)) {
                                throw new ApplicationException("取消送检核销--核销前的locTaskUuid为空");
                            } else if(StringUtil.isNull(targetLotCode)) {
                                throw new ApplicationException("取消送检核销--核销前的targetLotCode为空");
                            } else {
                                double qty1 = StringUtil.doubleTo0(lt.getQty());
                                double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                                double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                                double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                                double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                                double volume = StringUtil.doubleTo0(lt.getVolume());
                                StockWorkModel sw_out = new StockWorkModel();
                                LocationTaskModel lt_out = new LocationTaskModel();
                                new RemainSinworkModel();
                                List l = this.dao.createCommonQuery(RemainSinworkModel.class).addCondition(Condition.eq("inStockWorkUuid", lt.getInStockWorkUuid())).addCondition(Condition.eq("lotCode", lt.getSourceLotCode())).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
                                if(l != null && l.size() > 0) {
                                    RemainSinworkModel rs = (RemainSinworkModel)l.get(0);
                                    sw_out.setInStockWorkUuid(lt.getInStockWorkUuid());
                                    sw_out.setLotCode(lt.getSourceLotCode());
                                    sw_out.setQty(rs.getRemainQty());
                                    sw_out.setGrossWeight(rs.getRemainGrossWeight());
                                    sw_out.setNetWeight(rs.getRemainNetWeight());
                                    sw_out.setVolume(rs.getRemainVolume());
                                    sw_out.setSecondQty(rs.getRemainSecondQty());
                                    sw_out.setOfficeCode(rs.getOfficeCode());
                                    lt_out.setLocTaskType(lt.getLocTaskType());
                                    lt_out.setLogisticsOrderDetailUuid(lt.getLogisticsOrderDetailUuid());
                                    lt_out.setInLogisticsOrderDetailUuid(rs.getInLogisticsOrderDetailUuid());
                                    lt_out.setInStockWorkUuid(lt.getInStockWorkUuid());
                                    lt_out.setBatchNo(rs.getBatchNo());
                                    lt_out.setItemCode(rs.getItemCode());
                                    lt_out.setMarksNumber(rs.getMarksNumber());
                                    lt_out.setGoodsNature(rs.getGoodsNature());
                                    lt_out.setGoodsKind(rs.getGoodsKind());
                                    lt_out.setUnitCode(rs.getInstockUnitCode());
                                    lt_out.setTargetLotCode(lt.getSourceLotCode());
                                    lt_out.setQty(rs.getRemainQty());
                                    lt_out.setGrossWeight(rs.getRemainGrossWeight());
                                    lt_out.setNetWeight(rs.getRemainNetWeight());
                                    lt_out.setVolume(rs.getRemainVolume());
                                    lt_out.setSecondQty(rs.getRemainSecondQty());
                                    lt_out.setProductionDate(rs.getProductionDate());
                                    lt_out.setOfficeCode(rs.getOfficeCode());
                                    lt_out.setPackageNo(rs.getPackageNo());
                                    lt_out = this.setOutRemainSinwork(lt_out, sw_out);
                                    lt.setGrossWeight(lt_out.getGrossWeight());
                                    lt.setNetWeight(lt_out.getNetWeight());
                                    lt.setVolume(lt_out.getVolume());
                                    sw_out.setNetWeight(lt_out.getNetWeight());
                                    sw_out.setGrossWeight(lt_out.getGrossWeight());
                                    sw_out.setVolume(lt_out.getVolume());
                                    this.setOutRemainHold(lt_out, sw_out);
                                    lt.setInStockWorkUuid(inStockWorkUuid);
                                    lt.setBatchNo(batchNo);
                                    lt.setItemCode(itemCode);
                                    lt.setMarksNumber(marksNumber);
                                    lt.setGoodsNature(goodsNature);
                                    lt.setGoodsKind(goodsKind);
                                    lt.setUnitCode(unitCode);
                                    lt.setItemSeqno(itemSeqno);
                                    lt.setExtItemCode(extItemCode);
                                    lt.setPackageNo(packageNo);
                                    lt.setPanelNo(panelNo);
                                    StockWorkModel sw = new StockWorkModel();
                                    sw.setLocationTaskUuid(locTaskUuid);
                                    sw.setInStockWorkUuid(inStockWorkUuid);
                                    sw.setStockDate(this.sqlQueryManager.getSysDate(""));
                                    sw.setStockType("SIN");
                                    sw.setStockDesc("取消送检核销");
                                    sw.setQty(Double.valueOf(qty1));
                                    sw.setSecondQty(Double.valueOf(secondQty));
                                    sw.setThirdQty(Double.valueOf(thirdQty));
                                    sw.setNetWeight(lt.getNetWeight());
                                    sw.setGrossWeight(lt.getGrossWeight());
                                    sw.setVolume(lt.getVolume());
                                    sw.setLotCode(targetLotCode);
                                    sw.setLastStockWorkUuid("");
                                    sw.setControlWord(StringUtil.fixKey("", 20, "0"));
                                    sw.setOfficeCode(lt.getOfficeCode());
                                    sw = (StockWorkModel)this.dao.save(sw);
                                    sw = this.setInRemainSinwork(lt, sw);
                                    this.setInRemainHoldUuid(lt, sw);
                                    new LogisticsOrderDetailModel();
                                    List l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).addCondition(Condition.eq("transactionType", "SOC")).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
                                    if(l2 != null && l2.size() > 0) {
                                        LogisticsOrderDetailModel lod = (LogisticsOrderDetailModel)l2.get(0);
                                        lod.setDeliveredQty(Double.valueOf(StringUtil.doubleTo0(lod.getDeliveredQty()) - StringUtil.doubleTo0(sw.getQty())));
                                        lod.setDeliveredQty(StringUtil.ObjectToDouble(lod.getDeliveredQty(), 6));
                                        if(lod.getDeliveredQty().doubleValue() < 0.0D) {
                                            throw new ApplicationException("取消送检核销--已出库核销小于0");
                                        } else {
                                            lod = (LogisticsOrderDetailModel)this.dao.save(lod);
                                            String loUuid = lod.getLogisticsOrderUuid();
                                            if(!StringUtil.isNull(loUuid)) {
                                                if(this.getLodDeliveredQty(loUuid, "SOC") <= 0.0D) {
                                                    LogisticsOrderModel lo = (LogisticsOrderModel)this.dao.get(LogisticsOrderModel.class, loUuid);
                                                    if(lo.getCutOffDate() != null) {
                                                        lo.setCutOffDate((Date)null);
                                                        lo = (LogisticsOrderModel)this.dao.save(lo);
                                                    }
                                                }

                                                return lt;
                                            } else {
                                                throw new ApplicationException("取消送检核销--loUuid为空");
                                            }
                                        }
                                    } else {
                                        throw new ApplicationException("取消送检核销--无法检索到logisticsOrderDetailUuid");
                                    }
                                } else {
                                    throw new ApplicationException("取消送检核销 -- 没有检索到库存货物");
                                }
                            }
                        } else {
                            throw new ApplicationException("取消送检核销-无法检索核销前的记录");
                        }
                    } else {
                        throw new ApplicationException("取消送检核销--送检核销记录的LastLocationTaskUuid为空");
                    }
                } else {
                    throw new ApplicationException("取消送检核销--无法通过LastLocationTaskUuid检索到送检核销记录");
                }
            }
        }
    }

    private LocationTaskModel stupOpt(LocationTaskModel lt) {
        if(!lt.getLogisticsOrderDetailUuid().equals(lt.getInLogisticsOrderDetailUuid())) {
            throw new ApplicationException("InLogisticsOrderDetailUuid和LogisticsOrderDetailUuid数据不相同，无法操作");
        } else if(StringUtil.isNull(lt.getSourceLotCode())) {
            throw new ApplicationException("批量备货操作--源货位为空");
        } else {
            String ifSourceLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getSourceLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
            String ifSourceLotCode_flag = this.sqlQueryManager.getColumnData(ifSourceLotCodeSql, "", "");
            if(StringUtil.isNull(ifSourceLotCode_flag)) {
                throw new ApplicationException("批量备货操作--当前源货位[" + lt.getSourceLotCode() + "]不正确");
            } else {
                String ifTargetLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getTargetLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
                String ifTargetLotCode_flag = this.sqlQueryManager.getColumnData(ifTargetLotCodeSql, "", "");
                if(StringUtil.isNull(ifTargetLotCode_flag)) {
                    throw new ApplicationException("批量备货操作--当前目的货位[" + lt.getTargetLotCode() + "]不正确");
                } else {
                    String CW3_Sql = "select lod.logistics_order_no from logistics_order_detail lod,logistics_order lo   where lo.logistics_order_uuid=lod.logistics_order_uuid  and lod.logistics_order_detail_uuid=\'" + lt.getLogisticsOrderDetailUuid() + "\' " + " and ( substrb(nvl(lod.control_word,\'000\'),3,1)=\'F\' or " + " substrb(nvl(lo.control_word,\'000\'),3,1)=\'F\' )  and lo.transaction_type in (\'SOT\',\'PDN\') ";
                    String CW3_flag = this.sqlQueryManager.getColumnData(CW3_Sql, "", "");
                    if(!StringUtil.isNull(CW3_flag)) {
                        throw new ApplicationException("批量备货操作--[" + CW3_flag + "]单已完结，不允许执行批量拣货操作");
                    } else {
                        String locTaskUuid = lt.getLocationTaskUuid();
                        double qty = StringUtil.doubleTo0(lt.getQty());
                        double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                        double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                        double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                        double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                        double volume = StringUtil.doubleTo0(lt.getVolume());
                        String sourceLotCode = lt.getSourceLotCode();
                        String targetLotCode = lt.getTargetLotCode();
                        String inStockWorkUuid = lt.getInStockWorkUuid();
                        if(StringUtil.isNull(inStockWorkUuid)) {
                            throw new ApplicationException("批量备货操作--inStockWorkUuid为空");
                        } else {
                            StockWorkModel sw_out = new StockWorkModel();
                            sw_out.setLocationTaskUuid(locTaskUuid);
                            sw_out.setInStockWorkUuid(inStockWorkUuid);
                            sw_out.setStockDate(this.sqlQueryManager.getSysDate(""));
                            sw_out.setStockType("SOT");
                            sw_out.setStockDesc("批量备货");
                            sw_out.setQty(Double.valueOf(qty));
                            sw_out.setSecondQty(Double.valueOf(secondQty));
                            sw_out.setThirdQty(Double.valueOf(thirdQty));
                            sw_out.setNetWeight(Double.valueOf(netWeight));
                            sw_out.setGrossWeight(Double.valueOf(grossWeight));
                            sw_out.setVolume(Double.valueOf(volume));
                            sw_out.setLotCode(sourceLotCode);
                            sw_out.setLastStockWorkUuid("");
                            sw_out.setControlWord(StringUtil.fixKey("", 20, "0"));
                            sw_out.setOfficeCode(lt.getOfficeCode());
                            if(StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                                lt.setLastLocationTaskUuid(this.getLastLocationTaskUuid(sw_out.getInStockWorkUuid()));
                                lt = (LocationTaskModel)this.dao.save(lt);
                            }

                            if(!StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                                new LocationTaskModel();
                                List ls_lt = this.dao.createCommonQuery(LocationTaskModel.class).addCondition(Condition.eq("locationTaskUuid", lt.getLastLocationTaskUuid())).query();
                                if(ls_lt != null && ls_lt.size() > 0) {
                                    LocationTaskModel sw_in = (LocationTaskModel)ls_lt.get(0);
                                    if(sw_in.getLocTaskType() != null && sw_in.getLocTaskType().equals("PICK")) {
                                        throw new ApplicationException("批量备货操作 -- 当前记录为拣货记录，不允许操作");
                                    }
                                }
                            }

                            lt = this.setOutRemainSinwork(lt, sw_out);
                            StockWorkModel sw_in1 = new StockWorkModel();
                            sw_in1.setLocationTaskUuid(locTaskUuid);
                            sw_in1.setInStockWorkUuid(inStockWorkUuid);
                            sw_in1.setStockDate(this.sqlQueryManager.getSysDate(""));
                            sw_in1.setStockType("SIN");
                            sw_in1.setStockDesc("批量备货");
                            sw_in1.setQty(Double.valueOf(qty));
                            sw_in1.setSecondQty(Double.valueOf(secondQty));
                            sw_in1.setThirdQty(Double.valueOf(thirdQty));
                            sw_in1.setNetWeight(lt.getNetWeight());
                            sw_in1.setGrossWeight(lt.getGrossWeight());
                            sw_in1.setVolume(lt.getVolume());
                            sw_in1.setLotCode(targetLotCode);
                            sw_in1.setLastStockWorkUuid("");
                            sw_in1.setControlWord(StringUtil.fixKey("", 20, "0"));
                            sw_in1.setOfficeCode(lt.getOfficeCode());
                            sw_in1.setLastStockWorkUuid(sw_out.getStockWorkUuid());
                            sw_in1 = (StockWorkModel)this.dao.save(sw_in1);
                            sw_in1.setInStockWorkUuid(sw_in1.getStockWorkUuid());
                            lt.setInStockWorkUuid(sw_in1.getStockWorkUuid());
                            lt = (LocationTaskModel)this.dao.save(lt);
                            this.setInRemainSinwork(lt, sw_in1);
                            return lt;
                        }
                    }
                }
            }
        }
    }

    private LocationTaskModel caspOpt(LocationTaskModel lt) {
        if(!lt.getLogisticsOrderDetailUuid().equals(lt.getInLogisticsOrderDetailUuid())) {
            throw new ApplicationException("InLogisticsOrderDetailUuid和LogisticsOrderDetailUuid数据不相同，无法操作");
        } else if(StringUtil.isNull(lt.getSourceLotCode())) {
            throw new ApplicationException("取消批量备货操作--源货位为空");
        } else {
            String ifSourceLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getSourceLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
            String ifSourceLotCode_flag = this.sqlQueryManager.getColumnData(ifSourceLotCodeSql, "", "");
            if(StringUtil.isNull(ifSourceLotCode_flag)) {
                throw new ApplicationException("取消批量备货操作--当前源货位[" + lt.getSourceLotCode() + "]不正确");
            } else {
                String ifTargetLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getTargetLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
                String ifTargetLotCode_flag = this.sqlQueryManager.getColumnData(ifTargetLotCodeSql, "", "");
                if(StringUtil.isNull(ifTargetLotCode_flag)) {
                    throw new ApplicationException("取消批量备货操作--当前目的货位[" + lt.getTargetLotCode() + "]不正确");
                } else {
                    String locTaskUuid = lt.getLocationTaskUuid();
                    double qty = StringUtil.doubleTo0(lt.getQty());
                    double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                    double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                    double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                    double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                    double volume = StringUtil.doubleTo0(lt.getVolume());
                    String sourceLotCode = lt.getSourceLotCode();
                    String targetLotCode = lt.getTargetLotCode();
                    String inStockWorkUuid = lt.getInStockWorkUuid();
                    if(StringUtil.isNull(inStockWorkUuid)) {
                        throw new ApplicationException("取消批量备货操作--inStockWorkUuid为空");
                    } else {
                        StockWorkModel sw_out = new StockWorkModel();
                        sw_out.setLocationTaskUuid(locTaskUuid);
                        sw_out.setInStockWorkUuid(inStockWorkUuid);
                        sw_out.setStockDate(this.sqlQueryManager.getSysDate(""));
                        sw_out.setStockType("SOT");
                        sw_out.setStockDesc("取消批量备货");
                        sw_out.setQty(Double.valueOf(qty));
                        sw_out.setSecondQty(Double.valueOf(secondQty));
                        sw_out.setThirdQty(Double.valueOf(thirdQty));
                        sw_out.setNetWeight(Double.valueOf(netWeight));
                        sw_out.setGrossWeight(Double.valueOf(grossWeight));
                        sw_out.setVolume(Double.valueOf(volume));
                        sw_out.setLotCode(sourceLotCode);
                        sw_out.setLastStockWorkUuid("");
                        sw_out.setControlWord(StringUtil.fixKey("", 20, "0"));
                        sw_out.setOfficeCode(lt.getOfficeCode());
                        if(StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                            lt.setLastLocationTaskUuid(this.getLastLocationTaskUuid(sw_out.getInStockWorkUuid()));
                            lt = (LocationTaskModel)this.dao.save(lt);
                        }

                        if(!StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                            new LocationTaskModel();
                            List ls_lt = this.dao.createCommonQuery(LocationTaskModel.class).addCondition(Condition.eq("locationTaskUuid", lt.getLastLocationTaskUuid())).query();
                            if(ls_lt != null && ls_lt.size() > 0) {
                                LocationTaskModel sw_in = (LocationTaskModel)ls_lt.get(0);
                                if(sw_in.getLocTaskType() != null && sw_in.getLocTaskType().equals("PICK")) {
                                    throw new ApplicationException("取消批量备货 -- 当前记录为拣货记录，不允许操作");
                                }
                            }
                        }

                        lt = this.setOutRemainSinwork(lt, sw_out);
                        StockWorkModel sw_in1 = new StockWorkModel();
                        sw_in1.setLocationTaskUuid(locTaskUuid);
                        sw_in1.setInStockWorkUuid(inStockWorkUuid);
                        sw_in1.setStockDate(this.sqlQueryManager.getSysDate(""));
                        sw_in1.setStockType("SIN");
                        sw_in1.setStockDesc("取消批量备货");
                        sw_in1.setQty(Double.valueOf(qty));
                        sw_in1.setSecondQty(Double.valueOf(secondQty));
                        sw_in1.setThirdQty(Double.valueOf(thirdQty));
                        sw_in1.setNetWeight(lt.getNetWeight());
                        sw_in1.setGrossWeight(lt.getGrossWeight());
                        sw_in1.setVolume(lt.getVolume());
                        sw_in1.setLotCode(targetLotCode);
                        sw_in1.setLastStockWorkUuid("");
                        sw_in1.setControlWord(StringUtil.fixKey("", 20, "0"));
                        sw_in1.setOfficeCode(lt.getOfficeCode());
                        sw_in1.setLastStockWorkUuid(sw_out.getStockWorkUuid());
                        sw_in1 = (StockWorkModel)this.dao.save(sw_in1);
                        sw_in1.setInStockWorkUuid(sw_in1.getStockWorkUuid());
                        lt.setInStockWorkUuid(sw_in1.getStockWorkUuid());
                        lt = (LocationTaskModel)this.dao.save(lt);
                        this.setInRemainSinwork(lt, sw_in1);
                        return lt;
                    }
                }
            }
        }
    }

    private LocationTaskModel pickOpt(LocationTaskModel lt) {
        if(StringUtil.isNull(lt.getSourceLotCode())) {
            throw new ApplicationException("出库拣货--源货位为空");
        } else if(StringUtil.isNull(lt.getTargetLotCode())) {
            throw new ApplicationException("出库拣货--拣货区为空");
        } else if(lt.getLogisticsOrderDetailUuid().equals(lt.getInLogisticsOrderDetailUuid())) {
            throw new ApplicationException("InLogisticsOrderDetailUuid和LogisticsOrderDetailUuid数据相同，无法操作");
        } else {
            String ifTargetLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getTargetLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
            String ifTargetLotCode_flag = this.sqlQueryManager.getColumnData(ifTargetLotCodeSql, "", "");
            if(StringUtil.isNull(ifTargetLotCode_flag)) {
                throw new ApplicationException("出库拣货--当前目的货位[" + lt.getTargetLotCode() + "]不正确");
            } else {
                String CW3_Sql = "select lo.logistics_order_no from logistics_order_detail lod,logistics_order lo   where lo.logistics_order_uuid=lod.logistics_order_uuid  and lod.logistics_order_detail_uuid=\'" + lt.getLogisticsOrderDetailUuid() + "\' " + " and substrb(nvl(lo.control_word,\'000\'),3,1)=\'F\' and lo.transaction_type in (\'SOT\',\'PDN\') ";
                String CW3_flag = this.sqlQueryManager.getColumnData(CW3_Sql, "", "");
                if(!StringUtil.isNull(CW3_flag)) {
                    throw new ApplicationException("出库拣货--[" + CW3_flag + "]单已完结，不允许执行出库拣货操作");
                } else {
                    String locTaskUuid = lt.getLocationTaskUuid();
                    double qty = StringUtil.doubleTo0(lt.getQty());
                    double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                    double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                    double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                    double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                    double volume = StringUtil.doubleTo0(lt.getVolume());
                    String sourceLotCode = lt.getSourceLotCode();
                    String targetLotCode = lt.getTargetLotCode();
                    String inStockWorkUuid = lt.getInStockWorkUuid();
                    if(StringUtil.isNull(inStockWorkUuid)) {
                        throw new ApplicationException("出库拣货--inStockWorkUuid为空");
                    } else {
                        StockWorkModel sw_out = new StockWorkModel();
                        sw_out.setLocationTaskUuid(locTaskUuid);
                        sw_out.setInStockWorkUuid(inStockWorkUuid);
                        sw_out.setStockDate(this.sqlQueryManager.getSysDate(""));
                        sw_out.setStockType("SOT");
                        sw_out.setStockDesc("拣货");
                        sw_out.setQty(Double.valueOf(qty));
                        sw_out.setSecondQty(Double.valueOf(secondQty));
                        sw_out.setThirdQty(Double.valueOf(thirdQty));
                        sw_out.setNetWeight(Double.valueOf(netWeight));
                        sw_out.setGrossWeight(Double.valueOf(grossWeight));
                        sw_out.setVolume(Double.valueOf(volume));
                        sw_out.setLotCode(sourceLotCode);
                        sw_out.setLastStockWorkUuid("");
                        sw_out.setControlWord(StringUtil.fixKey("", 20, "0"));
                        sw_out.setOfficeCode(lt.getOfficeCode());
                        sw_out = (StockWorkModel)this.dao.save(sw_out);
                        if(StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                            lt.setLastLocationTaskUuid(this.getLastLocationTaskUuid(sw_out.getInStockWorkUuid()));
                            lt = (LocationTaskModel)this.dao.save(lt);
                        }

                        if(!StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                            new LocationTaskModel();
                            List lod = this.dao.createCommonQuery(LocationTaskModel.class).addCondition(Condition.eq("locationTaskUuid", lt.getLastLocationTaskUuid())).query();
                            if(lod != null && lod.size() > 0) {
                                LocationTaskModel sw_in = (LocationTaskModel)lod.get(0);
                                if(sw_in.getLocTaskType() != null && sw_in.getLocTaskType().equals("PICK")) {
                                    throw new ApplicationException("出库拣货 -- 当前记录为拣货记录，不允许操作");
                                }
                            }
                        }

                        lt = this.setOutRemainSinwork(lt, sw_out);
                        StockWorkModel sw_in1 = new StockWorkModel();
                        sw_in1.setLocationTaskUuid(locTaskUuid);
                        sw_in1.setInStockWorkUuid(inStockWorkUuid);
                        sw_in1.setStockDate(this.sqlQueryManager.getSysDate(""));
                        sw_in1.setStockType("SIN");
                        sw_in1.setStockDesc("拣货");
                        sw_in1.setQty(Double.valueOf(qty));
                        sw_in1.setSecondQty(Double.valueOf(secondQty));
                        sw_in1.setThirdQty(Double.valueOf(thirdQty));
                        sw_in1.setNetWeight(lt.getNetWeight());
                        sw_in1.setGrossWeight(lt.getGrossWeight());
                        sw_in1.setVolume(lt.getVolume());
                        sw_in1.setLotCode(targetLotCode);
                        sw_in1.setLastStockWorkUuid("");
                        sw_in1.setControlWord(StringUtil.fixKey("", 20, "0"));
                        sw_in1.setOfficeCode(lt.getOfficeCode());
                        sw_in1.setLastStockWorkUuid(sw_out.getStockWorkUuid());
                        sw_in1 = (StockWorkModel)this.dao.save(sw_in1);
                        sw_in1.setInStockWorkUuid(sw_in1.getStockWorkUuid());
                        lt.setInStockWorkUuid(sw_in1.getStockWorkUuid());
                        lt = (LocationTaskModel)this.dao.save(lt);
                        sw_in1 = this.setInRemainSinwork(lt, sw_in1);
                        new LogisticsOrderDetailModel();
                        List l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).addCondition(Condition.or(new Condition[]{Condition.eq("transactionType", "SOT"), Condition.eq("transactionType", "SOC"), Condition.eq("transactionType", "BUY"), Condition.and(new Condition[]{Condition.eq("transactionType", "PDN"), Condition.eq("substr(controlWord, 1, 1)", "S")})})).query();
                        if(l2 != null && l2.size() > 0) {
                            LogisticsOrderDetailModel lod1 = (LogisticsOrderDetailModel)l2.get(0);
                            lod1.setConfirmedQty(Double.valueOf(StringUtil.doubleTo0(lod1.getConfirmedQty()) + StringUtil.doubleTo0(sw_in1.getQty())));
                            lod1.setConfirmedQty(StringUtil.ObjectToDouble(lod1.getConfirmedQty(), 6));
                            if(lod1.getConfirmedQty().doubleValue() > lod1.getQty().doubleValue() + 1.0E-6D) {
                                throw new ApplicationException("出库拣货--拣货数大于计划出库数");
                            } else {
                                lod1 = (LogisticsOrderDetailModel)this.dao.save(lod1);
                                if(!StringUtil.isNull(lt.getOtherPkUuid())) {
                                    List ls_lpd = this.dao.createCommonQuery(LocPlanDetailModel.class).addCondition(Condition.eq("locPlanDetailUuid", lt.getOtherPkUuid())).query();
                                    if(ls_lpd != null && ls_lpd.size() > 0) {
                                        LocPlanDetailModel lpd = (LocPlanDetailModel)ls_lpd.get(0);
                                        lpd.setConfirmedQty(Double.valueOf(StringUtil.doubleTo0(lpd.getConfirmedQty()) + StringUtil.doubleTo0(sw_in1.getQty())));
                                        lpd.setConfirmedQty(StringUtil.ObjectToDouble(lpd.getConfirmedQty(), 6));
                                        lpd = (LocPlanDetailModel)this.dao.save(lpd);
                                    }
                                }

                                return lt;
                            }
                        } else {
                            throw new ApplicationException("出库拣货--无法检索到logisticsOrderDetailUuid");
                        }
                    }
                }
            }
        }
    }

    private LocationTaskModel canpOpt(LocationTaskModel lt) {
        if(StringUtil.isNull(lt.getSourceLotCode())) {
            throw new ApplicationException("取消拣货--源货位为空");
        } else if(lt.getLogisticsOrderDetailUuid().equals(lt.getInLogisticsOrderDetailUuid())) {
            throw new ApplicationException("InLogisticsOrderDetailUuid和LogisticsOrderDetailUuid数据相同，无法操作");
        } else if(StringUtil.isNull(lt.getLastLocationTaskUuid())) {
            throw new ApplicationException("取消拣货--lastLocTaskUuid为空");
        } else {
            String inStockWorkUuid_sql = "select SW.STOCK_WORK_UUID   from location_task lt,stock_work sw,remain_sinwork rs  where lt.location_task_uuid=sw.location_task_uuid    and sw.stock_work_uuid=rs.in_stock_work_uuid    and sw.stock_type=\'SIN\'    and lt.loc_task_type=\'PICK\'    and lt.qty - nvl(rs.remain_qty,0) >=0    and lt.location_task_uuid=\'" + lt.getLastLocationTaskUuid() + "\'";
            String inStockWorkUuid = this.sqlQueryManager.getColumnData(inStockWorkUuid_sql, "", "");
            if(StringUtil.isNull(inStockWorkUuid)) {
                throw new ApplicationException("取消拣货--要取消的拣货记录库存数量不够或已做下一步操作");
            } else {
                String locTaskUuid = lt.getLocationTaskUuid();
                double qty = StringUtil.doubleTo0(lt.getQty());
                double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                double volume = StringUtil.doubleTo0(lt.getVolume());
                String sourceLotCode = lt.getSourceLotCode();
                String targetLotCode = lt.getTargetLotCode();
                String lastLocTaskUuid = lt.getLastLocationTaskUuid();
                List ls = this.dao.createCommonQuery(StockWorkModel.class).addCondition(Condition.eq("locationTaskUuid", lastLocTaskUuid)).addCondition(Condition.eq("stockDesc", "拣货")).query();
                if(ls != null && ls.size() >= 2) {
                    StockWorkModel sw_out = new StockWorkModel();
                    StockWorkModel sw_in = new StockWorkModel();

                    for(int new_locTaskUuid = 0; new_locTaskUuid < ls.size(); ++new_locTaskUuid) {
                        if(((StockWorkModel)ls.get(new_locTaskUuid)).getStockType().equals("SOT")) {
                            sw_in = (StockWorkModel)ls.get(new_locTaskUuid);
                        } else {
                            if(!((StockWorkModel)ls.get(new_locTaskUuid)).getStockType().equals("SIN")) {
                                throw new ApplicationException("取消拣货--StockWork记录不符合要求");
                            }

                            sw_out = (StockWorkModel)ls.get(new_locTaskUuid);
                            sw_out.setInStockWorkUuid(sw_out.getStockWorkUuid());
                        }
                    }

                    if(StringUtil.isNull(sw_out.getInStockWorkUuid())) {
                        throw new ApplicationException("取消拣货--sot.InStockWorkUuid为空");
                    } else if(StringUtil.isNull(sw_out.getLotCode())) {
                        throw new ApplicationException("取消拣货--sot.货位为空");
                    } else if(StringUtil.isNull(sw_in.getLotCode())) {
                        throw new ApplicationException("取消拣货--sin.货位为空");
                    } else if(!sw_out.getStockWorkUuid().equals(inStockWorkUuid)) {
                        throw new ApplicationException("取消拣货--InStockWorkUuid写入不对，不是原拣货InStockWorkUuid");
                    } else {
                        sw_out.setStockWorkUuid("");
                        sw_out.setInStockWorkUuid(inStockWorkUuid);
                        sw_out.setLocationTaskUuid(locTaskUuid);
                        sw_out.setStockDate(this.sqlQueryManager.getSysDate(""));
                        sw_out.setStockType("SOT");
                        sw_out.setStockDesc("取消拣货");
                        sw_out.setQty(Double.valueOf(qty));
                        sw_out.setSecondQty(Double.valueOf(secondQty));
                        sw_out.setThirdQty(Double.valueOf(thirdQty));
                        sw_out.setNetWeight(Double.valueOf(netWeight));
                        sw_out.setGrossWeight(Double.valueOf(grossWeight));
                        sw_out.setVolume(Double.valueOf(volume));
                        sw_out.setLastStockWorkUuid("");
                        sw_out.setControlWord(StringUtil.fixKey("", 20, "0"));
                        sw_out.setOfficeCode(lt.getOfficeCode());
                        lt = this.setOutRemainSinwork(lt, sw_out);
                        String var29 = "";
                        StockWorkManagerImpl.LTRemain ltrs = this.getHbRemainData(lt);
                        var29 = ltrs.getLocationTaskUuid();
                        if(!StringUtil.isNull(var29)) {
                            lt.setInStockWorkUuid(ltrs.getInStockWorkUuid());
                            lt.setTargetLotCode(ltrs.getLotCode());
                            lt.setInLogisticsOrderDetailUuid(ltrs.getInLogisticsOrderDetailUuid());
                            inStockWorkUuid = lt.getInStockWorkUuid();
                            targetLotCode = lt.getTargetLotCode();
                            locTaskUuid = var29;
                            sw_in.setInStockWorkUuid(lt.getInStockWorkUuid());
                        }

                        sw_in.setStockWorkUuid("");
                        sw_in.setLocationTaskUuid(locTaskUuid);
                        sw_in.setStockDate(this.sqlQueryManager.getSysDate(""));
                        sw_in.setStockType("SIN");
                        sw_in.setStockDesc("取消拣货");
                        sw_in.setQty(Double.valueOf(qty));
                        sw_in.setSecondQty(Double.valueOf(secondQty));
                        sw_in.setThirdQty(Double.valueOf(thirdQty));
                        sw_in.setNetWeight(lt.getNetWeight());
                        sw_in.setGrossWeight(lt.getGrossWeight());
                        sw_in.setVolume(lt.getVolume());
                        sw_in.setLotCode(targetLotCode);
                        sw_in.setLastStockWorkUuid(sw_out.getStockWorkUuid());
                        sw_in.setControlWord(StringUtil.fixKey("", 20, "0"));
                        sw_in.setOfficeCode(lt.getOfficeCode());
                        if(StringUtil.isNull(var29)) {
                            sw_in = (StockWorkModel)this.dao.save(sw_in);
                            sw_in.setInStockWorkUuid(sw_in.getStockWorkUuid());
                            lt.setInStockWorkUuid(sw_in.getStockWorkUuid());
                        }

                        lt = (LocationTaskModel)this.dao.save(lt);
                        sw_in = this.setInRemainSinwork(lt, sw_in);
                        new LogisticsOrderDetailModel();
                        List l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).addCondition(Condition.or(new Condition[]{Condition.eq("transactionType", "SOT"), Condition.eq("transactionType", "SOC"), Condition.eq("transactionType", "BUY"), Condition.and(new Condition[]{Condition.eq("transactionType", "PDN"), Condition.eq("substr(controlWord, 1, 1)", "S")})})).query();
                        if(l2 != null && l2.size() > 0) {
                            LogisticsOrderDetailModel lod = (LogisticsOrderDetailModel)l2.get(0);
                            lod.setConfirmedQty(Double.valueOf(StringUtil.doubleTo0(lod.getConfirmedQty()) - StringUtil.doubleTo0(sw_in.getQty())));
                            lod.setConfirmedQty(StringUtil.ObjectToDouble(lod.getConfirmedQty(), 6));
                            if(lod.getConfirmedQty().doubleValue() < 0.0D) {
                                throw new ApplicationException("取消拣货--已拣货数量小于0");
                            } else {
                                lod = (LogisticsOrderDetailModel)this.dao.save(lod);
                                if(!StringUtil.isNull(lt.getOtherPkUuid())) {
                                    List ls_lpd = this.dao.createCommonQuery(LocPlanDetailModel.class).addCondition(Condition.eq("locPlanDetailUuid", lt.getOtherPkUuid())).query();
                                    if(ls_lpd != null && ls_lpd.size() > 0) {
                                        LocPlanDetailModel lpd = (LocPlanDetailModel)ls_lpd.get(0);
                                        lpd.setConfirmedQty(Double.valueOf(StringUtil.doubleTo0(lpd.getConfirmedQty()) - StringUtil.doubleTo0(sw_in.getQty())));
                                        lpd.setConfirmedQty(StringUtil.ObjectToDouble(lpd.getConfirmedQty(), 6));
                                        lpd = (LocPlanDetailModel)this.dao.save(lpd);
                                    }
                                }

                                return lt;
                            }
                        } else {
                            throw new ApplicationException("取消拣货--无法检索到logisticsOrderDetailUuid");
                        }
                    }
                } else {
                    throw new ApplicationException("取消拣货--拣货StockWork记录为空或StockWork记录小于2条记录");
                }
            }
        }
    }

    private LocationTaskModel outvOpt(LocationTaskModel lt) {
        if(lt.getLogisticsOrderDetailUuid().equals(lt.getInLogisticsOrderDetailUuid())) {
            throw new ApplicationException("InLogisticsOrderDetailUuid和LogisticsOrderDetailUuid数据相同，无法操作");
        } else if(StringUtil.isNull(lt.getInStockWorkUuid())) {
            throw new ApplicationException("出库核销--inStockWorkUuid为空");
        } else if(StringUtil.isNull(lt.getLastLocationTaskUuid())) {
            throw new ApplicationException("出库核销--LastLocationTaskUuid为空");
        } else {
            String CW3_Sql = "select lod.logistics_order_no from logistics_order_detail lod,logistics_order lo   where lo.logistics_order_uuid=lod.logistics_order_uuid  and lod.logistics_order_detail_uuid=\'" + lt.getLogisticsOrderDetailUuid() + "\' " + " and ( substrb(nvl(lod.control_word,\'000\'),3,1)=\'F\' or " + " substrb(nvl(lo.control_word,\'000\'),3,1)=\'F\' )  and lo.transaction_type in (\'SOT\',\'PDN\') ";
            String CW3_flag = this.sqlQueryManager.getColumnData(CW3_Sql, "", "");
            if(!StringUtil.isNull(CW3_flag)) {
                throw new ApplicationException("出库核销--[" + CW3_flag + "]单已完结，不允许执行出库核销操作");
            } else {
                String locTaskUuid = lt.getLocationTaskUuid();
                double qty = StringUtil.doubleTo0(lt.getQty());
                double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                double volume = StringUtil.doubleTo0(lt.getVolume());
                String targetLotCode = lt.getTargetLotCode();
                String inStockWorkUuid = lt.getInStockWorkUuid();
                StockWorkModel sw = new StockWorkModel();
                sw.setLocationTaskUuid(locTaskUuid);
                sw.setInStockWorkUuid(inStockWorkUuid);
                sw.setStockDate(this.sqlQueryManager.getSysDate(""));
                sw.setStockType("SOT");
                sw.setStockDesc("出库核销");
                sw.setQty(Double.valueOf(qty));
                sw.setSecondQty(Double.valueOf(secondQty));
                sw.setThirdQty(Double.valueOf(thirdQty));
                sw.setNetWeight(Double.valueOf(netWeight));
                sw.setGrossWeight(Double.valueOf(grossWeight));
                sw.setVolume(Double.valueOf(volume));
                sw.setLotCode(targetLotCode);
                sw.setControlWord(StringUtil.fixKey("", 20, "0"));
                sw.setOfficeCode(lt.getOfficeCode());
                sw = (StockWorkModel)this.dao.save(sw);
                lt = this.setOutRemainSinwork(lt, sw);
                lt = (LocationTaskModel)this.dao.save(lt);
                sw.setNetWeight(lt.getNetWeight());
                sw.setGrossWeight(lt.getGrossWeight());
                sw.setVolume(lt.getVolume());
                this.setOutRemainHoldUuid(lt, sw);
                new LogisticsOrderDetailModel();
                List l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).addCondition(Condition.eq("transactionType", "SOT")).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
                if(l2 != null && l2.size() > 0) {
                    LogisticsOrderDetailModel lod = (LogisticsOrderDetailModel)l2.get(0);
                    lod.setDeliveredQty(Double.valueOf(StringUtil.doubleTo0(lod.getDeliveredQty()) + StringUtil.doubleTo0(sw.getQty())));
                    lod.setDeliveredQty(StringUtil.ObjectToDouble(lod.getDeliveredQty(), 6));
                    if(lod.getDeliveredQty().doubleValue() > lod.getQty().doubleValue() + 1.0E-6D) {
                        throw new ApplicationException("出库核销操作--出库核销数量大于计划出库数");
                    } else {
                        lod = (LogisticsOrderDetailModel)this.dao.save(lod);
                        String loUuid = lod.getLogisticsOrderUuid();
                        if(!StringUtil.isNull(loUuid)) {
                            LogisticsOrderModel lo = (LogisticsOrderModel)this.dao.get(LogisticsOrderModel.class, loUuid);
                            if(lo.getCutOffDate() == null) {
                                if(lt.getLocTaskDate() == null) {
                                    lo.setCutOffDate(this.dao.getSysDate());
                                } else {
                                    lo.setCutOffDate(lt.getLocTaskDate());
                                }

                                lo = (LogisticsOrderModel)this.dao.save(lo);
                            }

                            return lt;
                        } else {
                            throw new ApplicationException("出库核销操作--loUuid为空");
                        }
                    }
                } else {
                    throw new ApplicationException("出库核销操作--无法检索到logisticsOrderDetailUuid");
                }
            }
        }
    }

    private LocationTaskModel canvOpt(LocationTaskModel lt) {
        if(lt.getLogisticsOrderDetailUuid().equals(lt.getInLogisticsOrderDetailUuid())) {
            throw new ApplicationException("InLogisticsOrderDetailUuid和LogisticsOrderDetailUuid数据相同，无法操作");
        } else if(StringUtil.isNull(lt.getInStockWorkUuid())) {
            throw new ApplicationException("取消出库核销--InStockWorkUuid为空");
        } else if(StringUtil.isNull(lt.getLastLocationTaskUuid())) {
            throw new ApplicationException("取消出库核销--LastLocationTaskUuid为空");
        } else {
            String CW3_Sql = "select lod.logistics_order_no from logistics_order_detail lod,logistics_order lo   where lo.logistics_order_uuid=lod.logistics_order_uuid  and lod.logistics_order_detail_uuid=\'" + lt.getLogisticsOrderDetailUuid() + "\' " + " and substrb(nvl(lo.control_word,\'000\'),3,1)=\'F\' and lo.transaction_type in (\'SOT\',\'PDN\') ";
            String CW3_flag = this.sqlQueryManager.getColumnData(CW3_Sql, "", "");
            if(!StringUtil.isNull(CW3_flag)) {
                throw new ApplicationException("取消出库核销--[" + CW3_flag + "]单已完结，不允许执行取消出库核销操作");
            } else {
                new LocationTaskModel();
                new LocationTaskModel();
                String inStockWorkUuid = "";
                String locTaskUuid = "";
                String targetLotCode = "";
                List l_ltm = this.dao.createCommonQuery(LocationTaskModel.class).addCondition(Condition.eq("locationTaskUuid", lt.getLastLocationTaskUuid())).addCondition(Condition.eq("locTaskType", "OUTV")).query();
                if(l_ltm != null && l_ltm.size() > 0) {
                    LocationTaskModel ltm = (LocationTaskModel)l_ltm.get(0);
                    String qty = ltm.getLastLocationTaskUuid();
                    if(!StringUtil.isNull(qty)) {
                        List l_ltm2 = this.dao.createCommonQuery(LocationTaskModel.class).addCondition(Condition.eq("locationTaskUuid", qty)).query();
                        if(l_ltm2 != null && l_ltm2.size() > 0) {
                            LocationTaskModel ltm2 = (LocationTaskModel)l_ltm2.get(0);
                            inStockWorkUuid = ltm2.getInStockWorkUuid();
                            locTaskUuid = ltm2.getLocationTaskUuid();
                            targetLotCode = ltm2.getTargetLotCode();
                            if(StringUtil.isNull(inStockWorkUuid)) {
                                throw new ApplicationException("取消出库核销--核销前的InStockWorkUuid为空");
                            } else if(StringUtil.isNull(locTaskUuid)) {
                                throw new ApplicationException("取消出库核销--核销前的locTaskUuid为空");
                            } else if(StringUtil.isNull(targetLotCode)) {
                                throw new ApplicationException("取消出库核销--核销前的targetLotCode为空");
                            } else {
                                double qty1 = StringUtil.doubleTo0(lt.getQty());
                                double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                                double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                                double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                                double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                                double volume = StringUtil.doubleTo0(lt.getVolume());
                                StockWorkModel sw = new StockWorkModel();
                                sw.setLocationTaskUuid(locTaskUuid);
                                sw.setInStockWorkUuid(inStockWorkUuid);
                                sw.setStockDate(this.sqlQueryManager.getSysDate(""));
                                sw.setStockType("SIN");
                                sw.setStockDesc("取消出库核销");
                                sw.setQty(Double.valueOf(qty1));
                                sw.setSecondQty(Double.valueOf(secondQty));
                                sw.setThirdQty(Double.valueOf(thirdQty));
                                sw.setNetWeight(Double.valueOf(netWeight));
                                sw.setGrossWeight(Double.valueOf(grossWeight));
                                sw.setVolume(Double.valueOf(volume));
                                sw.setLotCode(targetLotCode);
                                sw.setLastStockWorkUuid("");
                                sw.setControlWord(StringUtil.fixKey("", 20, "0"));
                                sw.setOfficeCode(lt.getOfficeCode());
                                sw = (StockWorkModel)this.dao.save(sw);
                                sw = this.setInRemainSinwork(lt, sw);
                                this.setInRemainHoldUuid(lt, sw);
                                new LogisticsOrderDetailModel();
                                List l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).addCondition(Condition.eq("transactionType", "SOT")).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
                                if(l2 != null && l2.size() > 0) {
                                    LogisticsOrderDetailModel lod = (LogisticsOrderDetailModel)l2.get(0);
                                    lod.setDeliveredQty(Double.valueOf(StringUtil.doubleTo0(lod.getDeliveredQty()) - StringUtil.doubleTo0(sw.getQty())));
                                    lod.setDeliveredQty(StringUtil.ObjectToDouble(lod.getDeliveredQty(), 6));
                                    if(lod.getDeliveredQty().doubleValue() < 0.0D) {
                                        throw new ApplicationException("取消出库核销--已出库核销小于0");
                                    } else {
                                        lod = (LogisticsOrderDetailModel)this.dao.save(lod);
                                        String loUuid = lod.getLogisticsOrderUuid();
                                        if(!StringUtil.isNull(loUuid)) {
                                            if(this.getLodDeliveredQty(loUuid, "SOT") <= 0.0D) {
                                                LogisticsOrderModel lo = (LogisticsOrderModel)this.dao.get(LogisticsOrderModel.class, loUuid);
                                                if(lo.getCutOffDate() != null) {
                                                    lo.setCutOffDate((Date)null);
                                                    lo = (LogisticsOrderModel)this.dao.save(lo);
                                                }
                                            }

                                            return lt;
                                        } else {
                                            throw new ApplicationException("取消出库核销--loUuid为空");
                                        }
                                    }
                                } else {
                                    throw new ApplicationException("取消出库核销--无法检索到logisticsOrderDetailUuid");
                                }
                            }
                        } else {
                            throw new ApplicationException("取消出库核销-无法检索核销前的记录");
                        }
                    } else {
                        throw new ApplicationException("取消出库核销--出库核销记录的LastLocationTaskUuid为空");
                    }
                } else {
                    throw new ApplicationException("取消出库核销--无法通过LastLocationTaskUuid检索到出库核销记录");
                }
            }
        }
    }

    private LocationTaskModel ddevOpt(LocationTaskModel lt) {
        new LogisticsOrderDetailModel();
        List l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).query();
        if(l2 != null && l2.size() > 0) {
            LogisticsOrderDetailModel lod = (LogisticsOrderDetailModel)l2.get(0);
            lod.setConfirmedQty(Double.valueOf(StringUtil.doubleTo0(lod.getConfirmedQty()) + StringUtil.doubleTo0(lt.getQty())));
            lod.setConfirmedQty(StringUtil.ObjectToDouble(lod.getConfirmedQty(), 6));
            if(lod.getConfirmedQty().doubleValue() > lod.getQty().doubleValue() + 1.0E-6D) {
                throw new ApplicationException("直接交货核销--核销数量大于计划数量");
            }

            lod = (LogisticsOrderDetailModel)this.dao.save(lod);
            String loUuid = lod.getLogisticsOrderUuid();
            if(StringUtil.isNull(loUuid)) {
                throw new ApplicationException("直接交货核销--loUuid为空");
            }

            LogisticsOrderModel lo = (LogisticsOrderModel)this.dao.get(LogisticsOrderModel.class, loUuid);
            if(lo.getCutOffDate() == null) {
                lo.setCutOffDate(lt.getLocTaskDate());
                lo = (LogisticsOrderModel)this.dao.save(lo);
            }
        }

        return lt;
    }

    private LocationTaskModel cddeOpt(LocationTaskModel lt) {
        new LogisticsOrderDetailModel();
        List l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).query();
        if(l2 != null && l2.size() > 0) {
            LogisticsOrderDetailModel lod = (LogisticsOrderDetailModel)l2.get(0);
            lod.setConfirmedQty(Double.valueOf(StringUtil.doubleTo0(lod.getConfirmedQty()) - StringUtil.doubleTo0(lt.getQty())));
            lod.setConfirmedQty(StringUtil.ObjectToDouble(lod.getConfirmedQty(), 6));
            if(lod.getConfirmedQty().doubleValue() < 0.0D) {
                throw new ApplicationException("取消直接交货--已交货数量小于0");
            }

            lod = (LogisticsOrderDetailModel)this.dao.save(lod);
            String loUuid = lod.getLogisticsOrderUuid();
            if(StringUtil.isNull(loUuid)) {
                throw new ApplicationException("取消直接交货--loUuid为空");
            }

            if(this.getLodConfirmedQty(loUuid) <= 0.0D) {
                LogisticsOrderModel lo = (LogisticsOrderModel)this.dao.get(LogisticsOrderModel.class, loUuid);
                if(lo.getCutOffDate() != null) {
                    lo.setCutOffDate((Date)null);
                    lo = (LogisticsOrderModel)this.dao.save(lo);
                }
            }
        }

        return lt;
    }

    private LocationTaskModel buyaOpt(LocationTaskModel lt) {
        if(StringUtil.isNull(lt.getSourceLotCode())) {
            throw new ApplicationException("买卖单核销 -- 源货位为空");
        } else if(StringUtil.isNull(lt.getTargetLotCode())) {
            throw new ApplicationException("买卖单核销 -- 目的货位为空");
        } else if(StringUtil.isNull(lt.getInStockWorkUuid())) {
            throw new ApplicationException("买卖单核销 -- InStockWorkUuid为空");
        } else {
            String ifTargetLotCodeSql = "select bls.bas_lot_stock_uuid  from bas_lot_stock bls where bls.lot_code=\'" + lt.getTargetLotCode() + "\' " + " and bls.office_code=\'" + lt.getOfficeCode() + "\' " + " and bls.status=\'Active\' ";
            String ifTargetLotCode_flag = this.sqlQueryManager.getColumnData(ifTargetLotCodeSql, "", "");
            if(StringUtil.isNull(ifTargetLotCode_flag)) {
                throw new ApplicationException("买卖单核销 -- 当前货位[" + lt.getTargetLotCode() + "]不正确");
            } else {
                if(StringUtil.isNull(lt.getLastLocationTaskUuid())) {
                    lt.setLastLocationTaskUuid(this.getLastLocationTaskUuid(lt.getInStockWorkUuid()));
                }

                String locTaskUuid = lt.getLocationTaskUuid();
                double qty = StringUtil.doubleTo0(lt.getQty());
                double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                double volume = StringUtil.doubleTo0(lt.getVolume());
                String targetLotCode = lt.getTargetLotCode();
                String inStockWorkUuid = lt.getInStockWorkUuid();
                StockWorkModel sw_out = new StockWorkModel();
                LocationTaskModel lt_out = new LocationTaskModel();
                new RemainSinworkModel();
                List l = this.dao.createCommonQuery(RemainSinworkModel.class).addCondition(Condition.eq("inStockWorkUuid", inStockWorkUuid)).addCondition(Condition.eq("lotCode", lt.getSourceLotCode())).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
                if(l != null && l.size() > 0) {
                    RemainSinworkModel rs = (RemainSinworkModel)l.get(0);
                    sw_out.setInStockWorkUuid(inStockWorkUuid);
                    sw_out.setLotCode(lt.getSourceLotCode());
                    sw_out.setQty(rs.getRemainQty());
                    sw_out.setGrossWeight(rs.getRemainGrossWeight());
                    sw_out.setNetWeight(rs.getRemainNetWeight());
                    sw_out.setVolume(rs.getRemainVolume());
                    sw_out.setSecondQty(rs.getRemainSecondQty());
                    sw_out.setOfficeCode(rs.getOfficeCode());
                    lt_out.setLocTaskType(lt.getLocTaskType());
                    lt_out.setLogisticsOrderDetailUuid(lt.getLogisticsOrderDetailUuid());
                    lt_out.setInLogisticsOrderDetailUuid(rs.getInLogisticsOrderDetailUuid());
                    lt_out.setInStockWorkUuid(inStockWorkUuid);
                    lt_out.setBatchNo(rs.getBatchNo());
                    lt_out.setItemCode(rs.getItemCode());
                    lt_out.setMarksNumber(rs.getMarksNumber());
                    lt_out.setGoodsNature(rs.getGoodsNature());
                    lt_out.setGoodsKind(rs.getGoodsKind());
                    lt_out.setUnitCode(rs.getInstockUnitCode());
                    lt_out.setTargetLotCode(lt.getSourceLotCode());
                    lt_out.setQty(rs.getRemainQty());
                    lt_out.setGrossWeight(rs.getRemainGrossWeight());
                    lt_out.setNetWeight(rs.getRemainNetWeight());
                    lt_out.setVolume(rs.getRemainVolume());
                    lt_out.setSecondQty(rs.getRemainSecondQty());
                    lt_out.setProductionDate(rs.getProductionDate());
                    lt_out.setOfficeCode(rs.getOfficeCode());
                    lt_out.setPackageNo(rs.getPackageNo());
                    lt_out = this.setOutRemainSinwork(lt_out, sw_out);
                    lt.setGrossWeight(lt_out.getGrossWeight());
                    lt.setNetWeight(lt_out.getNetWeight());
                    lt.setVolume(lt_out.getVolume());
                    sw_out.setNetWeight(lt_out.getNetWeight());
                    sw_out.setGrossWeight(lt_out.getGrossWeight());
                    sw_out.setVolume(lt_out.getVolume());
                    this.setOutRemainHoldUuid(lt_out, sw_out);
                    String new_locTaskUuid = "";
                    StockWorkManagerImpl.LTRemain ltrs = this.getHbRemainData(lt);
                    new_locTaskUuid = ltrs.getLocationTaskUuid();
                    if(!StringUtil.isNull(new_locTaskUuid)) {
                        lt.setInStockWorkUuid(ltrs.getInStockWorkUuid());
                        lt.setTargetLotCode(ltrs.getLotCode());
                        lt.setInLogisticsOrderDetailUuid(ltrs.getInLogisticsOrderDetailUuid());
                        inStockWorkUuid = lt.getInStockWorkUuid();
                        targetLotCode = lt.getTargetLotCode();
                        locTaskUuid = new_locTaskUuid;
                    } else {
                        lt.setInLogisticsOrderDetailUuid(lt.getLogisticsOrderDetailUuid());
                    }

                    StockWorkModel sw_in = new StockWorkModel();
                    sw_in.setLocationTaskUuid(locTaskUuid);
                    sw_in.setInStockWorkUuid(inStockWorkUuid);
                    sw_in.setStockDate(this.sqlQueryManager.getSysDate(""));
                    sw_in.setStockType("SIN");
                    sw_in.setStockDesc("买卖单核销");
                    sw_in.setQty(Double.valueOf(qty));
                    sw_in.setSecondQty(Double.valueOf(secondQty));
                    sw_in.setThirdQty(Double.valueOf(thirdQty));
                    sw_in.setNetWeight(lt.getNetWeight());
                    sw_in.setGrossWeight(lt.getGrossWeight());
                    sw_in.setVolume(lt.getVolume());
                    sw_in.setLotCode(targetLotCode);
                    sw_in.setLastStockWorkUuid("");
                    sw_in.setControlWord(StringUtil.fixKey("", 20, "0"));
                    sw_in.setOfficeCode(lt.getOfficeCode());
                    if(StringUtil.isNull(new_locTaskUuid)) {
                        sw_in = (StockWorkModel)this.dao.save(sw_in);
                        sw_in.setInStockWorkUuid(sw_in.getStockWorkUuid());
                        lt.setInStockWorkUuid(sw_in.getStockWorkUuid());
                    } else {
                        lt.setInStockWorkUuid(inStockWorkUuid);
                    }

                    lt = (LocationTaskModel)this.dao.save(lt);
                    sw_in = this.setInRemainSinwork(lt, sw_in);
                    this.setInRemainHold(lt, sw_in);
                    new LogisticsOrderDetailModel();
                    List l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).addCondition(Condition.eq("transactionType", "BUY")).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
                    if(l2 != null && l2.size() > 0) {
                        LogisticsOrderDetailModel lod = (LogisticsOrderDetailModel)l2.get(0);
                        lod.setDeliveredQty(Double.valueOf(StringUtil.doubleTo0(lod.getDeliveredQty()) + StringUtil.doubleTo0(sw_in.getQty())));
                        lod.setDeliveredQty(StringUtil.ObjectToDouble(lod.getDeliveredQty(), 6));
                        if(lod.getDeliveredQty().doubleValue() > lod.getQty().doubleValue() + 1.0E-6D) {
                            throw new ApplicationException("买卖单核销--买卖单核销数量大于计划数");
                        } else {
                            lod = (LogisticsOrderDetailModel)this.dao.save(lod);
                            String loUuid = lod.getLogisticsOrderUuid();
                            if(!StringUtil.isNull(loUuid)) {
                                LogisticsOrderModel lo = (LogisticsOrderModel)this.dao.get(LogisticsOrderModel.class, loUuid);
                                if(lo.getCutOffDate() == null) {
                                    lo.setCutOffDate(lt.getLocTaskDate());
                                    lo = (LogisticsOrderModel)this.dao.save(lo);
                                }

                                return lt;
                            } else {
                                throw new ApplicationException("买卖单核销--loUuid为空");
                            }
                        }
                    } else {
                        throw new ApplicationException("买卖单核销--无法检索到logisticsOrderDetailUuid");
                    }
                } else {
                    throw new ApplicationException("买卖单核销 -- 没有检索到库存货物");
                }
            }
        }
    }

    private LocationTaskModel buycOpt(LocationTaskModel lt) {
        if(StringUtil.isNull(lt.getSourceLotCode())) {
            throw new ApplicationException("买卖单取消核销 -- 源货位为空");
        } else if(StringUtil.isNull(lt.getInStockWorkUuid())) {
            throw new ApplicationException("买卖单取消核销--InStockWorkUuid为空");
        } else if(StringUtil.isNull(lt.getLastLocationTaskUuid())) {
            throw new ApplicationException("买卖单取消核销--LastLocationTaskUuid为空");
        } else {
            String CW3_Sql = "select lod.logistics_order_no from logistics_order_detail lod,logistics_order lo   where lo.logistics_order_uuid=lod.logistics_order_uuid  and lod.logistics_order_detail_uuid=\'" + lt.getLogisticsOrderDetailUuid() + "\' " + " and ( substrb(nvl(lod.control_word,\'000\'),3,1)=\'F\' or " + " substrb(nvl(lo.control_word,\'000\'),3,1)=\'F\' )  and lo.transaction_type in (\'BUY\') ";
            String CW3_flag = this.sqlQueryManager.getColumnData(CW3_Sql, "", "");
            if(!StringUtil.isNull(CW3_flag)) {
                throw new ApplicationException("买卖单取消核销--[" + CW3_flag + "]单已完结，不允许执行取消送检核销操作");
            } else {
                new LocationTaskModel();
                new LocationTaskModel();
                String inStockWorkUuid = "";
                String locTaskUuid = "";
                String targetLotCode = "";
                String batchNo = "";
                String itemCode = "";
                String marksNumber = "";
                String goodsNature = "";
                String goodsKind = "";
                String unitCode = "";
                String itemSeqno = "";
                String extItemCode = "";
                String packageNo = "";
                String panelNo = "";
                String in_loduuid = "";
                List l_ltm = this.dao.createCommonQuery(LocationTaskModel.class).addCondition(Condition.eq("locationTaskUuid", lt.getLastLocationTaskUuid())).addCondition(Condition.eq("locTaskType", "BUYA")).query();
                if(l_ltm != null && l_ltm.size() > 0) {
                    LocationTaskModel ltm = (LocationTaskModel)l_ltm.get(0);
                    String qty = ltm.getLastLocationTaskUuid();
                    if(!StringUtil.isNull(qty)) {
                        List l_ltm2 = this.dao.createCommonQuery(LocationTaskModel.class).addCondition(Condition.eq("locationTaskUuid", qty)).query();
                        if(l_ltm2 != null && l_ltm2.size() > 0) {
                            LocationTaskModel ltm2 = (LocationTaskModel)l_ltm2.get(0);
                            inStockWorkUuid = ltm2.getInStockWorkUuid();
                            locTaskUuid = ltm2.getLocationTaskUuid();
                            targetLotCode = ltm2.getTargetLotCode();
                            batchNo = ltm2.getBatchNo();
                            itemCode = ltm2.getItemCode();
                            marksNumber = ltm2.getMarksNumber();
                            goodsNature = ltm2.getGoodsNature();
                            goodsKind = ltm2.getGoodsKind();
                            unitCode = ltm2.getUnitCode();
                            itemSeqno = ltm2.getItemSeqno();
                            extItemCode = ltm2.getExtItemCode();
                            packageNo = ltm2.getPackageNo();
                            panelNo = ltm2.getPanelNo();
                            in_loduuid = ltm2.getInLogisticsOrderDetailUuid();
                            if(StringUtil.isNull(inStockWorkUuid)) {
                                throw new ApplicationException("买卖单取消核销--核销前的InStockWorkUuid为空");
                            } else if(StringUtil.isNull(locTaskUuid)) {
                                throw new ApplicationException("买卖单取消核销--核销前的locTaskUuid为空");
                            } else if(StringUtil.isNull(targetLotCode)) {
                                throw new ApplicationException("买卖单取消核销--核销前的targetLotCode为空");
                            } else {
                                double qty1 = StringUtil.doubleTo0(lt.getQty());
                                double secondQty = StringUtil.doubleTo0(lt.getSecondQty());
                                double thirdQty = StringUtil.doubleTo0(lt.getThirdQty());
                                double netWeight = StringUtil.doubleTo0(lt.getNetWeight());
                                double grossWeight = StringUtil.doubleTo0(lt.getGrossWeight());
                                double volume = StringUtil.doubleTo0(lt.getVolume());
                                StockWorkModel sw_out = new StockWorkModel();
                                LocationTaskModel lt_out = new LocationTaskModel();
                                new RemainSinworkModel();
                                List l = this.dao.createCommonQuery(RemainSinworkModel.class).addCondition(Condition.eq("inStockWorkUuid", lt.getInStockWorkUuid())).addCondition(Condition.eq("lotCode", lt.getSourceLotCode())).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
                                if(l != null && l.size() > 0) {
                                    RemainSinworkModel rs = (RemainSinworkModel)l.get(0);
                                    sw_out.setInStockWorkUuid(lt.getInStockWorkUuid());
                                    sw_out.setLotCode(lt.getSourceLotCode());
                                    sw_out.setQty(lt.getQty());
                                    sw_out.setGrossWeight(lt.getGrossWeight());
                                    sw_out.setNetWeight(lt.getNetWeight());
                                    sw_out.setVolume(lt.getVolume());
                                    sw_out.setSecondQty(lt.getSecondQty());
                                    sw_out.setOfficeCode(rs.getOfficeCode());
                                    lt_out.setLocTaskType(lt.getLocTaskType());
                                    lt_out.setLogisticsOrderDetailUuid(lt.getLogisticsOrderDetailUuid());
                                    lt_out.setInLogisticsOrderDetailUuid(rs.getInLogisticsOrderDetailUuid());
                                    lt_out.setInStockWorkUuid(lt.getInStockWorkUuid());
                                    lt_out.setBatchNo(rs.getBatchNo());
                                    lt_out.setItemCode(rs.getItemCode());
                                    lt_out.setMarksNumber(rs.getMarksNumber());
                                    lt_out.setGoodsNature(rs.getGoodsNature());
                                    lt_out.setGoodsKind(rs.getGoodsKind());
                                    lt_out.setUnitCode(rs.getInstockUnitCode());
                                    lt_out.setTargetLotCode(lt.getSourceLotCode());
                                    lt_out.setQty(lt.getQty());
                                    lt_out.setGrossWeight(lt.getGrossWeight());
                                    lt_out.setNetWeight(lt.getNetWeight());
                                    lt_out.setVolume(lt.getVolume());
                                    lt_out.setSecondQty(lt.getSecondQty());
                                    lt_out.setProductionDate(rs.getProductionDate());
                                    lt_out.setOfficeCode(rs.getOfficeCode());
                                    lt_out.setPackageNo(rs.getPackageNo());
                                    lt_out = this.setOutRemainSinwork(lt_out, sw_out);
                                    lt.setGrossWeight(lt_out.getGrossWeight());
                                    lt.setNetWeight(lt_out.getNetWeight());
                                    lt.setVolume(lt_out.getVolume());
                                    sw_out.setNetWeight(lt_out.getNetWeight());
                                    sw_out.setGrossWeight(lt_out.getGrossWeight());
                                    sw_out.setVolume(lt_out.getVolume());
                                    this.setOutRemainHold(lt_out, sw_out);
                                    lt.setInStockWorkUuid(inStockWorkUuid);
                                    lt.setBatchNo(batchNo);
                                    lt.setItemCode(itemCode);
                                    lt.setMarksNumber(marksNumber);
                                    lt.setGoodsNature(goodsNature);
                                    lt.setGoodsKind(goodsKind);
                                    lt.setUnitCode(unitCode);
                                    lt.setItemSeqno(itemSeqno);
                                    lt.setExtItemCode(extItemCode);
                                    lt.setPackageNo(packageNo);
                                    lt.setPanelNo(panelNo);
                                    lt.setInLogisticsOrderDetailUuid(in_loduuid);
                                    StockWorkModel sw = new StockWorkModel();
                                    sw.setLocationTaskUuid(locTaskUuid);
                                    sw.setInStockWorkUuid(inStockWorkUuid);
                                    sw.setStockDate(this.sqlQueryManager.getSysDate(""));
                                    sw.setStockType("SIN");
                                    sw.setStockDesc("买卖单取消核销");
                                    sw.setQty(Double.valueOf(qty1));
                                    sw.setSecondQty(Double.valueOf(secondQty));
                                    sw.setThirdQty(Double.valueOf(thirdQty));
                                    sw.setNetWeight(lt.getNetWeight());
                                    sw.setGrossWeight(lt.getGrossWeight());
                                    sw.setVolume(lt.getVolume());
                                    sw.setLotCode(targetLotCode);
                                    sw.setLastStockWorkUuid("");
                                    sw.setControlWord(StringUtil.fixKey("", 20, "0"));
                                    sw.setOfficeCode(lt.getOfficeCode());
                                    sw = this.setInRemainSinwork(lt, sw);
                                    this.setInRemainHoldUuid(lt, sw);
                                    new LogisticsOrderDetailModel();
                                    List l2 = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).addCondition(Condition.eq("transactionType", "BUY")).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
                                    if(l2 != null && l2.size() > 0) {
                                        LogisticsOrderDetailModel lod = (LogisticsOrderDetailModel)l2.get(0);
                                        lod.setDeliveredQty(Double.valueOf(StringUtil.doubleTo0(lod.getDeliveredQty()) - StringUtil.doubleTo0(sw.getQty())));
                                        lod.setDeliveredQty(StringUtil.ObjectToDouble(lod.getDeliveredQty(), 6));
                                        if(lod.getDeliveredQty().doubleValue() < 0.0D) {
                                            throw new ApplicationException("买卖单取消核销--已出库核销小于0");
                                        } else {
                                            lod = (LogisticsOrderDetailModel)this.dao.save(lod);
                                            String loUuid = lod.getLogisticsOrderUuid();
                                            if(!StringUtil.isNull(loUuid)) {
                                                if(this.getLodDeliveredQty(loUuid, "BUY") <= 0.0D) {
                                                    LogisticsOrderModel lo = (LogisticsOrderModel)this.dao.get(LogisticsOrderModel.class, loUuid);
                                                    if(lo.getCutOffDate() != null) {
                                                        lo.setCutOffDate((Date)null);
                                                        lo = (LogisticsOrderModel)this.dao.save(lo);
                                                    }
                                                }

                                                return lt;
                                            } else {
                                                throw new ApplicationException("买卖单取消核销--loUuid为空");
                                            }
                                        }
                                    } else {
                                        throw new ApplicationException("买卖单取消核销--无法检索到logisticsOrderDetailUuid");
                                    }
                                } else {
                                    throw new ApplicationException("买卖单取消核销 -- 没有检索到库存货物");
                                }
                            }
                        } else {
                            throw new ApplicationException("买卖单取消核销-无法检索核销前的记录");
                        }
                    } else {
                        throw new ApplicationException("买卖单取消核销--送检核销记录的LastLocationTaskUuid为空");
                    }
                } else {
                    throw new ApplicationException("买卖单取消核销--无法通过LastLocationTaskUuid检索到送检核销记录");
                }
            }
        }
    }

    private StockWorkModel setInRemainSinwork(LocationTaskModel lt, StockWorkModel sw) {
        RemainSinworkModel rs = new RemainSinworkModel();
        List l = this.dao.createCommonQuery(RemainSinworkModel.class).addCondition(Condition.eq("inStockWorkUuid", sw.getInStockWorkUuid())).addCondition(Condition.eq("lotCode", sw.getLotCode())).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
        if(l != null && l.size() > 0) {
            rs = (RemainSinworkModel)l.get(0);
            if(!rs.getInLogisticsOrderDetailUuid().equals(lt.getInLogisticsOrderDetailUuid())) {
                throw new ApplicationException("lt的InLogisticsOrderDetailUuid与库存不一致");
            }

            if(!lt.getLocTaskType().equals("RECE")) {
                double base_volume = 0.0D;
                double base_grossweight = 0.0D;
                double base_netweight = 0.0D;
                double rs_qty = StringUtil.doubleTo0(rs.getRemainQty());
                double rs_volume = StringUtil.doubleTo0(rs.getRemainVolume());
                double rs_grossweight = StringUtil.doubleTo0(rs.getRemainGrossWeight());
                double rs_netweight = StringUtil.doubleTo0(rs.getRemainNetWeight());
                if(rs_qty > 0.0D) {
                    if(rs_volume > 0.0D) {
                        base_volume = StringUtil.ObjectToDouble(Double.valueOf(rs_volume / rs_qty), 8).doubleValue();
                    }

                    if(rs_grossweight > 0.0D) {
                        base_grossweight = StringUtil.ObjectToDouble(Double.valueOf(rs_grossweight / rs_qty), 8).doubleValue();
                    }

                    if(rs_netweight > 0.0D) {
                        base_netweight = StringUtil.ObjectToDouble(Double.valueOf(rs_netweight / rs_qty), 8).doubleValue();
                    }

                    sw.setVolume(StringUtil.ObjectToDouble(Double.valueOf(sw.getQty().doubleValue() * base_volume), 8));
                    sw.setGrossWeight(StringUtil.ObjectToDouble(Double.valueOf(sw.getQty().doubleValue() * base_grossweight), 8));
                    sw.setNetWeight(StringUtil.ObjectToDouble(Double.valueOf(sw.getQty().doubleValue() * base_netweight), 8));
                }
            }

            if(lt.getLocTaskType().equals("RECE")) {
                rs.setInstockQty(Double.valueOf(StringUtil.doubleTo0(rs.getInstockQty()) + StringUtil.doubleTo0(sw.getQty())));
                rs.setInstockSecondQty(Double.valueOf(StringUtil.doubleTo0(rs.getInstockSecondQty()) + StringUtil.doubleTo0(sw.getSecondQty())));
                rs.setInstockNetWeight(Double.valueOf(StringUtil.doubleTo0(rs.getInstockNetWeight()) + StringUtil.doubleTo0(sw.getNetWeight())));
                rs.setInstockGrossWeight(Double.valueOf(StringUtil.doubleTo0(rs.getInstockGrossWeight()) + StringUtil.doubleTo0(sw.getGrossWeight())));
                rs.setInstockVolume(Double.valueOf(StringUtil.doubleTo0(rs.getInstockVolume()) + StringUtil.doubleTo0(sw.getVolume())));
            }

            rs.setRemainQty(Double.valueOf(StringUtil.doubleTo0(rs.getRemainQty()) + StringUtil.doubleTo0(sw.getQty())));
            rs.setRemainSecondQty(Double.valueOf(StringUtil.doubleTo0(rs.getRemainSecondQty()) + StringUtil.doubleTo0(sw.getSecondQty())));
            rs.setRemainNetWeight(Double.valueOf(StringUtil.doubleTo0(rs.getRemainNetWeight()) + StringUtil.doubleTo0(sw.getNetWeight())));
            rs.setRemainGrossWeight(Double.valueOf(StringUtil.doubleTo0(rs.getRemainGrossWeight()) + StringUtil.doubleTo0(sw.getGrossWeight())));
            rs.setRemainVolume(Double.valueOf(StringUtil.doubleTo0(rs.getRemainVolume()) + StringUtil.doubleTo0(sw.getVolume())));
            rs.setLength(lt.getLength());
            rs.setWidth(lt.getWidth());
            rs.setHeight(lt.getHeight());
        } else {
            rs.setRemainSinworkUuid("");
            rs.setInLogisticsOrderDetailUuid(lt.getInLogisticsOrderDetailUuid());
            rs.setInStockWorkUuid(sw.getInStockWorkUuid());
            rs.setInstockUnitCode(lt.getUnitCode());
            rs.setInstockUnitDesc(lt.getUnitDesc());
            rs.setInstockSecondUnitDesc(lt.getUnitDesc());
            rs.setInstockSecondUnitCode(lt.getSecondUnitCode());
            rs.setInstockSecondUnitDesc(lt.getSecondUnitDesc());
            rs.setInstockThirdUnitCode(lt.getThirdUnitCode());
            rs.setInstockThirdUnitDesc(lt.getThirdUnitDesc());
            rs.setInstockQty(Double.valueOf(StringUtil.doubleTo0(sw.getQty())));
            rs.setInstockSecondQty(Double.valueOf(StringUtil.doubleTo0(sw.getSecondQty())));
            rs.setInstockThirdQty(Double.valueOf(StringUtil.doubleTo0(sw.getThirdQty())));
            rs.setInstockNetWeight(Double.valueOf(StringUtil.doubleTo0(sw.getNetWeight())));
            rs.setInstockGrossWeight(Double.valueOf(StringUtil.doubleTo0(sw.getGrossWeight())));
            rs.setInstockVolume(Double.valueOf(StringUtil.doubleTo0(sw.getVolume())));
            rs.setRemainQty(Double.valueOf(StringUtil.doubleTo0(sw.getQty())));
            rs.setRemainSecondQty(Double.valueOf(StringUtil.doubleTo0(sw.getSecondQty())));
            rs.setRemainThirdQty(Double.valueOf(StringUtil.doubleTo0(sw.getThirdQty())));
            rs.setRemainNetWeight(Double.valueOf(StringUtil.doubleTo0(sw.getNetWeight())));
            rs.setRemainGrossWeight(Double.valueOf(StringUtil.doubleTo0(sw.getGrossWeight())));
            rs.setRemainVolume(Double.valueOf(StringUtil.doubleTo0(sw.getVolume())));
            rs.setLotCode(sw.getLotCode());
            rs.setBatchNo(lt.getBatchNo());
            rs.setItemCode(lt.getItemCode());
            rs.setItemSeqno(lt.getItemSeqno());
            rs.setExtItemCode(lt.getExtItemCode());
            rs.setGoodsDesc(lt.getGoodsDesc());
            rs.setMarksNumber(lt.getMarksNumber());
            rs.setPackageNo(lt.getPackageNo());
            rs.setBarcode(lt.getBarcode());
            rs.setPanelNo(lt.getPanelNo());
            rs.setOfficeCode(lt.getOfficeCode());
            rs.setGoodsKind(lt.getGoodsKind());
            rs.setGoodsNature(lt.getGoodsNature());
            rs.setProductionDate(lt.getProductionDate());
            rs.setLength(lt.getLength());
            rs.setWidth(lt.getWidth());
            rs.setHeight(lt.getHeight());
        }

        rs = (RemainSinworkModel)this.dao.save(rs);
        return sw;
    }

    private LocationTaskModel setOutRemainSinwork(LocationTaskModel lt, StockWorkModel sw) {
        new RemainSinworkModel();
        List l = this.dao.createCommonQuery(RemainSinworkModel.class).addCondition(Condition.eq("inStockWorkUuid", sw.getInStockWorkUuid())).addCondition(Condition.eq("lotCode", sw.getLotCode())).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
        if(l != null && l.size() > 0) {
            RemainSinworkModel rs = (RemainSinworkModel)l.get(0);
            if(StringUtil.doubleTo0(rs.getRemainQty()) < StringUtil.doubleTo0(sw.getQty())) {
                throw new ApplicationException("库存数小于操作数");
            } else {
                double base_volume = 0.0D;
                double base_grossweight = 0.0D;
                double base_netweight = 0.0D;
                double rs_qty = StringUtil.doubleTo0(rs.getRemainQty());
                double rs_volume = StringUtil.doubleTo0(rs.getRemainVolume());
                double rs_grossweight = StringUtil.doubleTo0(rs.getRemainGrossWeight());
                double rs_netweight = StringUtil.doubleTo0(rs.getRemainNetWeight());
                if(rs_qty > 0.0D) {
                    if(rs_volume > 0.0D) {
                        base_volume = StringUtil.ObjectToDouble(Double.valueOf(rs_volume / rs_qty), 8).doubleValue();
                    }

                    if(rs_grossweight > 0.0D) {
                        base_grossweight = StringUtil.ObjectToDouble(Double.valueOf(rs_grossweight / rs_qty), 8).doubleValue();
                    }

                    if(rs_netweight > 0.0D) {
                        base_netweight = StringUtil.ObjectToDouble(Double.valueOf(rs_netweight / rs_qty), 8).doubleValue();
                    }

                    sw.setVolume(StringUtil.ObjectToDouble(Double.valueOf(sw.getQty().doubleValue() * base_volume), 6));
                    sw.setGrossWeight(StringUtil.ObjectToDouble(Double.valueOf(sw.getQty().doubleValue() * base_grossweight), 6));
                    sw.setNetWeight(StringUtil.ObjectToDouble(Double.valueOf(sw.getQty().doubleValue() * base_netweight), 6));
                }

                if(!rs.getInLogisticsOrderDetailUuid().equals(lt.getInLogisticsOrderDetailUuid())) {
                    throw new ApplicationException("lt的InLogisticsOrderDetailUuid与库存不一致");
                } else {
                    rs.setRemainQty(Double.valueOf(StringUtil.doubleTo0(rs.getRemainQty()) - StringUtil.doubleTo0(sw.getQty())));
                    rs.setRemainSecondQty(Double.valueOf(StringUtil.doubleTo0(rs.getRemainSecondQty()) - StringUtil.doubleTo0(sw.getSecondQty())));
                    rs.setRemainNetWeight(Double.valueOf(StringUtil.doubleTo0(rs.getRemainNetWeight()) - StringUtil.doubleTo0(sw.getNetWeight())));
                    rs.setRemainGrossWeight(Double.valueOf(StringUtil.doubleTo0(rs.getRemainGrossWeight()) - StringUtil.doubleTo0(sw.getGrossWeight())));
                    rs.setRemainVolume(Double.valueOf(StringUtil.doubleTo0(rs.getRemainVolume()) - StringUtil.doubleTo0(sw.getVolume())));
                    lt.setVolume(Double.valueOf(StringUtil.doubleTo0(sw.getVolume())));
                    lt.setGrossWeight(Double.valueOf(StringUtil.doubleTo0(sw.getGrossWeight())));
                    lt.setNetWeight(Double.valueOf(StringUtil.doubleTo0(sw.getNetWeight())));
                    lt.setSecondQty(Double.valueOf(StringUtil.doubleTo0(sw.getSecondQty())));
                    if(lt.getLocTaskType().equals("CANR") || lt.getLocTaskType().equals("DELR")) {
                        rs.setInstockQty(Double.valueOf(StringUtil.doubleTo0(rs.getInstockQty()) - StringUtil.doubleTo0(sw.getQty())));
                        rs.setInstockSecondQty(Double.valueOf(StringUtil.doubleTo0(rs.getInstockSecondQty()) - StringUtil.doubleTo0(sw.getSecondQty())));
                        rs.setInstockNetWeight(Double.valueOf(StringUtil.doubleTo0(rs.getInstockNetWeight()) - StringUtil.doubleTo0(sw.getNetWeight())));
                        rs.setInstockGrossWeight(Double.valueOf(StringUtil.doubleTo0(rs.getInstockGrossWeight()) - StringUtil.doubleTo0(sw.getGrossWeight())));
                        rs.setInstockVolume(Double.valueOf(StringUtil.doubleTo0(rs.getInstockVolume()) - StringUtil.doubleTo0(sw.getVolume())));
                    }

                    if(StringUtil.doubleTo0(rs.getRemainQty()) < 0.0D) {
                        throw new ApplicationException("库存数为负数，无法处理");
                    } else {
                        if(StringUtil.doubleTo0(rs.getRemainQty()) == 0.0D) {
                            this.dao.remove(rs);
                        } else {
                            rs = (RemainSinworkModel)this.dao.save(rs);
                        }

                        return lt;
                    }
                }
            }
        } else {
            throw new ApplicationException("没有检索到库存货物，无法操作");
        }
    }

    private RemainHoldModel setInRemainHold(LocationTaskModel lt, StockWorkModel sw) {
        RemainHoldModel rh = new RemainHoldModel();
        Object l = new ArrayList();
        String rhUuid = this.checkRemainHold(lt);
        if(!StringUtil.isNull(rhUuid)) {
            l = this.dao.createCommonQuery(RemainHoldModel.class).addCondition(Condition.eq("remainHoldUuid", rhUuid)).query();
        }

        if(l != null && ((List)l).size() > 0) {
            rh = (RemainHoldModel)((List)l).get(0);
            rh.setRemainQty(Double.valueOf(StringUtil.doubleTo0(rh.getRemainQty()) + StringUtil.doubleTo0(sw.getQty())));
            rh.setRemainSecondQty(Double.valueOf(StringUtil.doubleTo0(rh.getRemainSecondQty()) + StringUtil.doubleTo0(sw.getSecondQty())));
            rh.setRemainNetWeight(Double.valueOf(StringUtil.doubleTo0(rh.getRemainNetWeight()) + StringUtil.doubleTo0(sw.getNetWeight())));
            rh.setRemainGrossWeight(Double.valueOf(StringUtil.doubleTo0(rh.getRemainGrossWeight()) + StringUtil.doubleTo0(sw.getGrossWeight())));
            rh.setRemainVolume(Double.valueOf(StringUtil.doubleTo0(rh.getRemainVolume()) + StringUtil.doubleTo0(sw.getVolume())));
            if(lt.getLocTaskType().equals("RECE")) {
                rh.setInstockQty(Double.valueOf(StringUtil.doubleTo0(rh.getInstockQty()) + StringUtil.doubleTo0(sw.getQty())));
                rh.setInstockSecondQty(Double.valueOf(StringUtil.doubleTo0(rh.getInstockSecondQty()) + StringUtil.doubleTo0(sw.getSecondQty())));
                rh.setInstockNetWeight(Double.valueOf(StringUtil.doubleTo0(rh.getInstockNetWeight()) + StringUtil.doubleTo0(sw.getNetWeight())));
                rh.setInstockGrossWeight(Double.valueOf(StringUtil.doubleTo0(rh.getInstockGrossWeight()) + StringUtil.doubleTo0(sw.getGrossWeight())));
                rh.setInstockVolume(Double.valueOf(StringUtil.doubleTo0(rh.getInstockVolume()) + StringUtil.doubleTo0(sw.getVolume())));
            }

            rh = (RemainHoldModel)this.dao.save(rh);
        } else {
            RemainHoldModel new_rh = new RemainHoldModel();
            new_rh.setRemainHoldUuid("");
            new_rh.setInLogisticsOrderDetailUuid(lt.getInLogisticsOrderDetailUuid());
            new_rh.setInstockUnitCode(lt.getUnitCode());
            new_rh.setInstockUnitDesc(lt.getUnitDesc());
            new_rh.setInstockSecondUnitDesc(lt.getUnitDesc());
            new_rh.setInstockSecondUnitCode(lt.getSecondUnitCode());
            new_rh.setInstockSecondUnitDesc(lt.getSecondUnitDesc());
            new_rh.setInstockThirdUnitCode(lt.getThirdUnitCode());
            new_rh.setInstockThirdUnitDesc(lt.getThirdUnitDesc());
            new_rh.setInstockQty(Double.valueOf(StringUtil.doubleTo0(sw.getQty())));
            new_rh.setInstockSecondQty(Double.valueOf(StringUtil.doubleTo0(sw.getSecondQty())));
            new_rh.setInstockThirdQty(Double.valueOf(StringUtil.doubleTo0(sw.getThirdQty())));
            new_rh.setInstockNetWeight(Double.valueOf(StringUtil.doubleTo0(sw.getNetWeight())));
            new_rh.setInstockGrossWeight(Double.valueOf(StringUtil.doubleTo0(sw.getGrossWeight())));
            new_rh.setInstockVolume(Double.valueOf(StringUtil.doubleTo0(sw.getVolume())));
            new_rh.setRemainQty(Double.valueOf(StringUtil.doubleTo0(sw.getQty())));
            new_rh.setRemainSecondQty(Double.valueOf(StringUtil.doubleTo0(sw.getSecondQty())));
            new_rh.setRemainThirdQty(Double.valueOf(StringUtil.doubleTo0(sw.getThirdQty())));
            new_rh.setRemainNetWeight(Double.valueOf(StringUtil.doubleTo0(sw.getNetWeight())));
            new_rh.setRemainGrossWeight(Double.valueOf(StringUtil.doubleTo0(sw.getGrossWeight())));
            new_rh.setRemainVolume(Double.valueOf(StringUtil.doubleTo0(sw.getVolume())));
            new_rh.setBatchNo(lt.getBatchNo());
            new_rh.setItemCode(lt.getItemCode());
            new_rh.setItemSeqno(lt.getItemSeqno());
            new_rh.setExtItemCode(lt.getExtItemCode());
            new_rh.setGoodsDesc(lt.getGoodsDesc());
            new_rh.setMarksNumber(lt.getMarksNumber());
            new_rh.setPackageNo(lt.getPackageNo());
            new_rh.setBarcode(lt.getBarcode());
            new_rh.setOfficeCode(lt.getOfficeCode());
            new_rh.setGoodsKind(lt.getGoodsKind());
            new_rh.setGoodsNature(lt.getGoodsNature());
            new_rh.setProductionDate(lt.getProductionDate());
            new_rh = (RemainHoldModel)this.dao.save(new_rh);
        }

        return rh;
    }

    private RemainHoldModel setOutRemainHold(LocationTaskModel lt, StockWorkModel sw) {
        String rhUuid = this.checkRemainHold(lt);
        if(StringUtil.isNull(rhUuid)) {
            throw new ApplicationException("没有检索到可办单库存货物（LT记录与库存记录不匹对)，无法操作");
        } else {
            new RemainHoldModel();
            List l = this.dao.createCommonQuery(RemainHoldModel.class).addCondition(Condition.eq("remainHoldUuid", rhUuid)).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
            if(l != null && l.size() > 0) {
                RemainHoldModel rh = (RemainHoldModel)l.get(0);
                rh.setRemainQty(Double.valueOf(StringUtil.doubleTo0(rh.getRemainQty()) - StringUtil.doubleTo0(sw.getQty())));
                rh.setRemainSecondQty(Double.valueOf(StringUtil.doubleTo0(rh.getRemainSecondQty()) - StringUtil.doubleTo0(sw.getSecondQty())));
                rh.setRemainNetWeight(Double.valueOf(StringUtil.doubleTo0(rh.getRemainNetWeight()) - StringUtil.doubleTo0(sw.getNetWeight())));
                rh.setRemainGrossWeight(Double.valueOf(StringUtil.doubleTo0(rh.getRemainGrossWeight()) - StringUtil.doubleTo0(sw.getGrossWeight())));
                rh.setRemainVolume(Double.valueOf(StringUtil.doubleTo0(rh.getRemainVolume()) - StringUtil.doubleTo0(sw.getVolume())));
                if(lt.getLocTaskType().equals("CANR") || lt.getLocTaskType().equals("DELR")) {
                    rh.setInstockQty(Double.valueOf(StringUtil.doubleTo0(rh.getInstockQty()) - StringUtil.doubleTo0(sw.getQty())));
                    rh.setInstockSecondQty(Double.valueOf(StringUtil.doubleTo0(rh.getInstockSecondQty()) - StringUtil.doubleTo0(sw.getSecondQty())));
                    rh.setInstockNetWeight(Double.valueOf(StringUtil.doubleTo0(rh.getInstockNetWeight()) - StringUtil.doubleTo0(sw.getNetWeight())));
                    rh.setInstockGrossWeight(Double.valueOf(StringUtil.doubleTo0(rh.getInstockGrossWeight()) - StringUtil.doubleTo0(sw.getGrossWeight())));
                    rh.setInstockVolume(Double.valueOf(StringUtil.doubleTo0(rh.getInstockVolume()) - StringUtil.doubleTo0(sw.getVolume())));
                }

                rh = (RemainHoldModel)this.dao.save(rh);
                if(StringUtil.doubleTo0(rh.getRemainQty()) <= 0.0D && StringUtil.doubleTo0(rh.getHoldQty()) <= 0.0D) {
                    this.dao.remove(rh);
                }

                return rh;
            } else {
                throw new ApplicationException("没有检索到可办单库存（HOLD)货物，无法操作");
            }
        }
    }

    private RemainHoldModel setOutRemainHoldUuid(LocationTaskModel lt, StockWorkModel sw) {
        new LogisticsOrderDetailModel();
        if(StringUtil.isNull(lt.getLogisticsOrderDetailUuid())) {
            throw new ApplicationException("OutRemainHoldUuid-LogisticsOrderDetailUuid为空");
        } else {
            List lod_list = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).query();
            if(lod_list != null && lod_list.size() > 0) {
                LogisticsOrderDetailModel lod = (LogisticsOrderDetailModel)lod_list.get(0);
                if(StringUtil.isNull(lod.getRemainHoldUuid())) {
                    throw new ApplicationException("OutRemainHoldUuid-RemainHoldUuid为空");
                } else if(StringUtil.isNull(lt.getInLogisticsOrderDetailUuid())) {
                    throw new ApplicationException("OutRemainHoldUuid-InLogisticsOrderDetailUuid为空");
                } else {
                    new RemainHoldModel();
                    List l = this.dao.createCommonQuery(RemainHoldModel.class).addCondition(Condition.eq("remainHoldUuid", lod.getRemainHoldUuid())).query();
                    if(l != null && l.size() > 0) {
                        RemainHoldModel rh = (RemainHoldModel)l.get(0);
                        rh.setHoldQty(Double.valueOf(StringUtil.doubleTo0(rh.getHoldQty()) - StringUtil.doubleTo0(sw.getQty())));
                        rh.setHoldSecondQty(Double.valueOf(StringUtil.doubleTo0(rh.getHoldSecondQty()) - StringUtil.doubleTo0(sw.getSecondQty())));
                        rh.setHoldNetWeight(Double.valueOf(StringUtil.doubleTo0(rh.getHoldNetWeight()) - StringUtil.doubleTo0(sw.getNetWeight())));
                        rh.setHoldGrossWeight(Double.valueOf(StringUtil.doubleTo0(rh.getHoldGrossWeight()) - StringUtil.doubleTo0(sw.getGrossWeight())));
                        rh.setHoldVolume(Double.valueOf(StringUtil.doubleTo0(rh.getHoldVolume()) - StringUtil.doubleTo0(sw.getVolume())));
                        rh = (RemainHoldModel)this.dao.save(rh);
                        String rhUuid = this.checkRemainHold(lt);
                        if(StringUtil.isNull(rhUuid)) {
                            throw new ApplicationException("没有检索到可办单库存货物（LT记录与库存记录不匹对)，无法操作");
                        } else {
                            new RemainHoldModel();
                            List inl = this.dao.createCommonQuery(RemainHoldModel.class).addCondition(Condition.eq("remainHoldUuid", rhUuid)).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
                            if(inl != null && inl.size() > 0) {
                                RemainHoldModel inlodrh = (RemainHoldModel)inl.get(0);
                                inlodrh.setRemainQty(Double.valueOf(StringUtil.doubleTo0(inlodrh.getRemainQty()) - StringUtil.doubleTo0(sw.getQty())));
                                inlodrh.setRemainSecondQty(Double.valueOf(StringUtil.doubleTo0(inlodrh.getRemainSecondQty()) - StringUtil.doubleTo0(sw.getSecondQty())));
                                inlodrh.setRemainNetWeight(Double.valueOf(StringUtil.doubleTo0(inlodrh.getRemainNetWeight()) - StringUtil.doubleTo0(sw.getNetWeight())));
                                inlodrh.setRemainGrossWeight(Double.valueOf(StringUtil.doubleTo0(inlodrh.getRemainGrossWeight()) - StringUtil.doubleTo0(sw.getGrossWeight())));
                                inlodrh.setRemainVolume(Double.valueOf(StringUtil.doubleTo0(inlodrh.getRemainVolume()) - StringUtil.doubleTo0(sw.getVolume())));
                                inlodrh = (RemainHoldModel)this.dao.save(inlodrh);
                                if(inlodrh.getRemainQty().doubleValue() <= 0.0D && inlodrh.getHoldQty().doubleValue() <= 0.0D) {
                                    this.dao.remove(inlodrh);
                                }

                                return rh;
                            } else {
                                throw new ApplicationException("没有检索Hold表中的InLod库存货物，无法操作");
                            }
                        }
                    } else {
                        throw new ApplicationException("没有检索到可办单库存（HOLD)货物，无法操作");
                    }
                }
            } else {
                throw new ApplicationException("OutRemainHoldUuid-无法检索到logisticsOrderDetailUuid");
            }
        }
    }

    private RemainHoldModel setInRemainHoldUuid(LocationTaskModel lt, StockWorkModel sw) {
        new LogisticsOrderDetailModel();
        if(StringUtil.isNull(lt.getLogisticsOrderDetailUuid())) {
            throw new ApplicationException("OutRemainHoldUuid-LogisticsOrderDetailUuid为空");
        } else {
            List lod_list = this.dao.createCommonQuery(LogisticsOrderDetailModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", lt.getLogisticsOrderDetailUuid())).query();
            if(lod_list != null && lod_list.size() > 0) {
                LogisticsOrderDetailModel lod = (LogisticsOrderDetailModel)lod_list.get(0);
                if(StringUtil.isNull(lod.getRemainHoldUuid())) {
                    throw new ApplicationException("OutRemainHoldUuid-RemainHoldUuid为空");
                } else if(StringUtil.isNull(lt.getInLogisticsOrderDetailUuid())) {
                    throw new ApplicationException("OutRemainHoldUuid-InLogisticsOrderDetailUuid为空");
                } else {
                    RemainHoldModel rh = new RemainHoldModel();
                    List l = this.dao.createCommonQuery(RemainHoldModel.class).addCondition(Condition.eq("remainHoldUuid", lod.getRemainHoldUuid())).query();
                    if(l != null && l.size() > 0) {
                        rh = (RemainHoldModel)l.get(0);
                        double inlodrh2 = 0.0D;
                        double rhUuid2 = 0.0D;
                        double new_hold_uuid1 = 0.0D;
                        double rh_qty = StringUtil.doubleTo0(rh.getRemainQty());
                        double rh_volume = StringUtil.doubleTo0(rh.getRemainVolume());
                        double rh_grossweight = StringUtil.doubleTo0(rh.getRemainGrossWeight());
                        double rh_netweight = StringUtil.doubleTo0(rh.getRemainNetWeight());
                        double swrh_volume = StringUtil.doubleTo0(sw.getVolume());
                        double swrh_grossweight = StringUtil.doubleTo0(sw.getGrossWeight());
                        double swrh_netweight = StringUtil.doubleTo0(sw.getNetWeight());
                        if(rh_qty > 0.0D) {
                            if(rh_volume > 0.0D) {
                                inlodrh2 = StringUtil.ObjectToDouble(Double.valueOf(rh_volume / rh_qty), 8).doubleValue();
                            }

                            if(rh_grossweight > 0.0D) {
                                rhUuid2 = StringUtil.ObjectToDouble(Double.valueOf(rh_grossweight / rh_qty), 8).doubleValue();
                            }

                            if(rh_netweight > 0.0D) {
                                new_hold_uuid1 = StringUtil.ObjectToDouble(Double.valueOf(rh_netweight / rh_qty), 8).doubleValue();
                            }

                            swrh_volume = StringUtil.ObjectToDouble(Double.valueOf(sw.getQty().doubleValue() * inlodrh2), 8).doubleValue();
                            swrh_grossweight = StringUtil.ObjectToDouble(Double.valueOf(sw.getQty().doubleValue() * rhUuid2), 8).doubleValue();
                            swrh_netweight = StringUtil.ObjectToDouble(Double.valueOf(sw.getQty().doubleValue() * new_hold_uuid1), 8).doubleValue();
                        }

                        rh.setHoldQty(Double.valueOf(StringUtil.doubleTo0(rh.getHoldQty()) + StringUtil.doubleTo0(sw.getQty())));
                        rh.setHoldSecondQty(Double.valueOf(StringUtil.doubleTo0(rh.getHoldSecondQty()) + StringUtil.doubleTo0(sw.getSecondQty())));
                        rh.setHoldNetWeight(Double.valueOf(StringUtil.doubleTo0(rh.getHoldNetWeight()) + swrh_netweight));
                        rh.setHoldGrossWeight(Double.valueOf(StringUtil.doubleTo0(rh.getHoldGrossWeight()) + swrh_grossweight));
                        rh.setHoldVolume(Double.valueOf(StringUtil.doubleTo0(rh.getHoldVolume()) + swrh_volume));
                        rh = (RemainHoldModel)this.dao.save(rh);
                        new RemainHoldModel();
                        Object inl1 = new ArrayList();
                        String rhUuid1 = this.checkRemainHold(lt);
                        if(!StringUtil.isNull(rhUuid1)) {
                            inl1 = this.dao.createCommonQuery(RemainHoldModel.class).addCondition(Condition.eq("remainHoldUuid", rhUuid1)).query();
                        }

                        if(inl1 != null && ((List)inl1).size() > 0) {
                            RemainHoldModel inlodrh1 = (RemainHoldModel)((List)inl1).get(0);
                            double new_rh3 = 0.0D;
                            double inbase_grossweight = 0.0D;
                            double inbase_netweight = 0.0D;
                            double inrh_qty = StringUtil.doubleTo0(inlodrh1.getInstockQty());
                            double inrh_volume = StringUtil.doubleTo0(inlodrh1.getInstockVolume());
                            double inrh_grossweight = StringUtil.doubleTo0(inlodrh1.getInstockGrossWeight());
                            double inrh_netweight = StringUtil.doubleTo0(inlodrh1.getInstockNetWeight());
                            double inswrh_volume = StringUtil.doubleTo0(sw.getVolume());
                            double inswrh_grossweight = StringUtil.doubleTo0(sw.getGrossWeight());
                            double inswrh_netweight = StringUtil.doubleTo0(sw.getNetWeight());
                            if(inrh_qty > 0.0D) {
                                if(inrh_volume > 0.0D) {
                                    new_rh3 = StringUtil.ObjectToDouble(Double.valueOf(inrh_volume / inrh_qty), 8).doubleValue();
                                }

                                if(inrh_grossweight > 0.0D) {
                                    inbase_grossweight = StringUtil.ObjectToDouble(Double.valueOf(inrh_grossweight / inrh_qty), 8).doubleValue();
                                }

                                if(inrh_netweight > 0.0D) {
                                    inbase_netweight = StringUtil.ObjectToDouble(Double.valueOf(inrh_netweight / inrh_qty), 8).doubleValue();
                                }

                                inswrh_volume = StringUtil.ObjectToDouble(Double.valueOf(sw.getQty().doubleValue() * new_rh3), 8).doubleValue();
                                inswrh_grossweight = StringUtil.ObjectToDouble(Double.valueOf(sw.getQty().doubleValue() * inbase_grossweight), 8).doubleValue();
                                inswrh_netweight = StringUtil.ObjectToDouble(Double.valueOf(sw.getQty().doubleValue() * inbase_netweight), 8).doubleValue();
                            }

                            inlodrh1.setRemainQty(Double.valueOf(StringUtil.doubleTo0(inlodrh1.getRemainQty()) + StringUtil.doubleTo0(sw.getQty())));
                            inlodrh1.setRemainSecondQty(Double.valueOf(StringUtil.doubleTo0(inlodrh1.getRemainSecondQty()) + StringUtil.doubleTo0(sw.getSecondQty())));
                            inlodrh1.setRemainNetWeight(Double.valueOf(StringUtil.doubleTo0(inlodrh1.getRemainNetWeight()) + inswrh_netweight));
                            inlodrh1.setRemainGrossWeight(Double.valueOf(StringUtil.doubleTo0(inlodrh1.getRemainGrossWeight()) + inswrh_grossweight));
                            inlodrh1.setRemainVolume(Double.valueOf(StringUtil.doubleTo0(inlodrh1.getRemainVolume()) + inswrh_volume));
                            inlodrh1 = (RemainHoldModel)this.dao.save(inlodrh1);
                        } else {
                            RemainHoldModel new_rh1 = new RemainHoldModel();
                            new_rh1.setRemainHoldUuid("");
                            new_rh1.setInLogisticsOrderDetailUuid(lt.getInLogisticsOrderDetailUuid());
                            new_rh1.setInstockUnitCode(lt.getUnitCode());
                            new_rh1.setInstockUnitDesc(lt.getUnitDesc());
                            new_rh1.setInstockSecondUnitDesc(lt.getUnitDesc());
                            new_rh1.setInstockSecondUnitCode(lt.getSecondUnitCode());
                            new_rh1.setInstockSecondUnitDesc(lt.getSecondUnitDesc());
                            new_rh1.setInstockThirdUnitCode(lt.getThirdUnitCode());
                            new_rh1.setInstockThirdUnitDesc(lt.getThirdUnitDesc());
                            new_rh1.setInstockQty(Double.valueOf(StringUtil.doubleTo0(sw.getQty())));
                            new_rh1.setInstockSecondQty(Double.valueOf(StringUtil.doubleTo0(sw.getSecondQty())));
                            new_rh1.setInstockThirdQty(Double.valueOf(StringUtil.doubleTo0(sw.getThirdQty())));
                            new_rh1.setInstockNetWeight(Double.valueOf(StringUtil.doubleTo0(sw.getNetWeight())));
                            new_rh1.setInstockGrossWeight(Double.valueOf(StringUtil.doubleTo0(sw.getGrossWeight())));
                            new_rh1.setInstockVolume(Double.valueOf(StringUtil.doubleTo0(sw.getVolume())));
                            new_rh1.setRemainQty(Double.valueOf(StringUtil.doubleTo0(sw.getQty())));
                            new_rh1.setRemainSecondQty(Double.valueOf(StringUtil.doubleTo0(sw.getSecondQty())));
                            new_rh1.setRemainThirdQty(Double.valueOf(StringUtil.doubleTo0(sw.getThirdQty())));
                            new_rh1.setRemainNetWeight(Double.valueOf(StringUtil.doubleTo0(sw.getNetWeight())));
                            new_rh1.setRemainGrossWeight(Double.valueOf(StringUtil.doubleTo0(sw.getGrossWeight())));
                            new_rh1.setRemainVolume(Double.valueOf(StringUtil.doubleTo0(sw.getVolume())));
                            new_rh1.setBatchNo(lt.getBatchNo());
                            new_rh1.setItemCode(lt.getItemCode());
                            new_rh1.setItemSeqno(lt.getItemSeqno());
                            new_rh1.setExtItemCode(lt.getExtItemCode());
                            new_rh1.setGoodsDesc(lt.getGoodsDesc());
                            new_rh1.setMarksNumber(lt.getMarksNumber());
                            new_rh1.setPackageNo(lt.getPackageNo());
                            new_rh1.setBarcode(lt.getBarcode());
                            new_rh1.setOfficeCode(lt.getOfficeCode());
                            new_rh1.setGoodsKind(lt.getGoodsKind());
                            new_rh1.setGoodsNature(lt.getGoodsNature());
                            new_rh1.setProductionDate(lt.getProductionDate());
                            new_rh1 = (RemainHoldModel)this.dao.save(new_rh1);
                        }
                    } else {
                        new RemainHoldModel();
                        Object inl = new ArrayList();
                        String rhUuid = this.checkRemainHold(lt);
                        if(!StringUtil.isNull(rhUuid)) {
                            inl = this.dao.createCommonQuery(RemainHoldModel.class).addCondition(Condition.eq("remainHoldUuid", rhUuid)).query();
                        }

                        if(inl != null && ((List)inl).size() > 0) {
                            RemainHoldModel inlodrh = (RemainHoldModel)((List)inl).get(0);
                            inlodrh.setHoldQty(Double.valueOf(StringUtil.doubleTo0(inlodrh.getHoldQty()) + StringUtil.doubleTo0(sw.getQty())));
                            inlodrh.setHoldSecondQty(Double.valueOf(StringUtil.doubleTo0(inlodrh.getHoldSecondQty()) + StringUtil.doubleTo0(sw.getSecondQty())));
                            inlodrh.setHoldNetWeight(Double.valueOf(StringUtil.doubleTo0(inlodrh.getHoldNetWeight()) + StringUtil.doubleTo0(sw.getNetWeight())));
                            inlodrh.setHoldGrossWeight(Double.valueOf(StringUtil.doubleTo0(inlodrh.getHoldGrossWeight()) + StringUtil.doubleTo0(sw.getGrossWeight())));
                            inlodrh.setHoldVolume(Double.valueOf(StringUtil.doubleTo0(inlodrh.getHoldVolume()) + StringUtil.doubleTo0(sw.getVolume())));
                            inlodrh.setRemainQty(Double.valueOf(StringUtil.doubleTo0(inlodrh.getRemainQty()) + StringUtil.doubleTo0(sw.getQty())));
                            inlodrh.setRemainSecondQty(Double.valueOf(StringUtil.doubleTo0(inlodrh.getRemainSecondQty()) + StringUtil.doubleTo0(sw.getSecondQty())));
                            inlodrh.setRemainNetWeight(Double.valueOf(StringUtil.doubleTo0(inlodrh.getRemainNetWeight()) + StringUtil.doubleTo0(sw.getNetWeight())));
                            inlodrh.setRemainGrossWeight(Double.valueOf(StringUtil.doubleTo0(inlodrh.getRemainGrossWeight()) + StringUtil.doubleTo0(sw.getGrossWeight())));
                            inlodrh.setRemainVolume(Double.valueOf(StringUtil.doubleTo0(inlodrh.getRemainVolume()) + StringUtil.doubleTo0(sw.getVolume())));
                            inlodrh = (RemainHoldModel)this.dao.save(inlodrh);
                            String new_rh2 = "update logistics_order_detail lod set lod.remain_hold_uuid=\'" + inlodrh.getRemainHoldUuid() + "\'," + "lod.rec_ver=lod.rec_ver + 1 where lod.logistics_order_detail_uuid=\'" + lt.getLogisticsOrderDetailUuid() + "\'";
                            this.sqlQueryManager.executeSQL(new_rh2, "", true);
                        } else {
                            RemainHoldModel new_rh = new RemainHoldModel();
                            new_rh.setRemainHoldUuid("");
                            new_rh.setInLogisticsOrderDetailUuid(lt.getInLogisticsOrderDetailUuid());
                            new_rh.setInstockUnitCode(lt.getUnitCode());
                            new_rh.setInstockUnitDesc(lt.getUnitDesc());
                            new_rh.setInstockSecondUnitDesc(lt.getUnitDesc());
                            new_rh.setInstockSecondUnitCode(lt.getSecondUnitCode());
                            new_rh.setInstockSecondUnitDesc(lt.getSecondUnitDesc());
                            new_rh.setInstockThirdUnitCode(lt.getThirdUnitCode());
                            new_rh.setInstockThirdUnitDesc(lt.getThirdUnitDesc());
                            new_rh.setInstockQty(Double.valueOf(StringUtil.doubleTo0(sw.getQty())));
                            new_rh.setInstockSecondQty(Double.valueOf(StringUtil.doubleTo0(sw.getSecondQty())));
                            new_rh.setInstockThirdQty(Double.valueOf(StringUtil.doubleTo0(sw.getThirdQty())));
                            new_rh.setInstockNetWeight(Double.valueOf(StringUtil.doubleTo0(sw.getNetWeight())));
                            new_rh.setInstockGrossWeight(Double.valueOf(StringUtil.doubleTo0(sw.getGrossWeight())));
                            new_rh.setInstockVolume(Double.valueOf(StringUtil.doubleTo0(sw.getVolume())));
                            new_rh.setHoldQty(Double.valueOf(StringUtil.doubleTo0(sw.getQty())));
                            new_rh.setHoldSecondQty(Double.valueOf(StringUtil.doubleTo0(sw.getSecondQty())));
                            new_rh.setHoldThirdQty(Double.valueOf(StringUtil.doubleTo0(sw.getThirdQty())));
                            new_rh.setHoldNetWeight(Double.valueOf(StringUtil.doubleTo0(sw.getNetWeight())));
                            new_rh.setHoldGrossWeight(Double.valueOf(StringUtil.doubleTo0(sw.getGrossWeight())));
                            new_rh.setHoldVolume(Double.valueOf(StringUtil.doubleTo0(sw.getVolume())));
                            new_rh.setRemainQty(Double.valueOf(StringUtil.doubleTo0(sw.getQty())));
                            new_rh.setRemainSecondQty(Double.valueOf(StringUtil.doubleTo0(sw.getSecondQty())));
                            new_rh.setRemainThirdQty(Double.valueOf(StringUtil.doubleTo0(sw.getThirdQty())));
                            new_rh.setRemainNetWeight(Double.valueOf(StringUtil.doubleTo0(sw.getNetWeight())));
                            new_rh.setRemainGrossWeight(Double.valueOf(StringUtil.doubleTo0(sw.getGrossWeight())));
                            new_rh.setRemainVolume(Double.valueOf(StringUtil.doubleTo0(sw.getVolume())));
                            new_rh.setBatchNo(lt.getBatchNo());
                            new_rh.setItemCode(lt.getItemCode());
                            new_rh.setItemSeqno(lt.getItemSeqno());
                            new_rh.setExtItemCode(lt.getExtItemCode());
                            new_rh.setGoodsDesc(lt.getGoodsDesc());
                            new_rh.setMarksNumber(lt.getMarksNumber());
                            new_rh.setPackageNo(lt.getPackageNo());
                            new_rh.setBarcode(lt.getBarcode());
                            new_rh.setOfficeCode(lt.getOfficeCode());
                            new_rh.setGoodsKind(lt.getGoodsKind());
                            new_rh.setGoodsNature(lt.getGoodsNature());
                            new_rh.setProductionDate(lt.getProductionDate());
                            new_rh = (RemainHoldModel)this.dao.save(new_rh);
                            String new_hold_uuid = new_rh.getRemainHoldUuid();
                            String updateString = "update remain_hold rh set rh.remain_hold_uuid=\'" + lod.getRemainHoldUuid() + "\'" + ",rh.rec_ver=rh.rec_ver + 1  where rh.remain_hold_uuid=\'" + new_hold_uuid + "\' ";
                            this.sqlQueryManager.executeSQL(updateString, "", true);
                        }
                    }

                    return rh;
                }
            } else {
                throw new ApplicationException("OutRemainHoldUuid-无法检索到logisticsOrderDetailUuid");
            }
        }
    }

    public boolean PorcessRemainHold() {
        boolean result = false;
        return result;
    }

    public boolean PorcessRemainHoldInLodUuid(String InLogisticsOrderDetailUuid, String RemainHoldUuid) {
        boolean result = false;
        return result;
    }

    private String getLastLocationTaskUuid(String instockWorkUuid) {
        String LastLocationTaskUuid = "";
        if(!StringUtil.isNull(instockWorkUuid)) {
            String str_sql = "select lt.location_task_uuid from location_task lt,stock_work sw,remain_sinwork rs  where lt.location_task_uuid=sw.location_task_uuid  and sw.stock_work_uuid=rs.in_stock_work_uuid  and rs.in_stock_work_uuid=\'" + instockWorkUuid + "\' and lt.loc_task_type<>\'PICK\' and rownum=1 ";
            LastLocationTaskUuid = this.sqlQueryManager.getColumnData(str_sql, "", "");
        }

        return LastLocationTaskUuid;
    }

    private StockWorkManagerImpl.LTRemain getHbRemainData(LocationTaskModel lt) {
        StockWorkManagerImpl.LTRemain result_ltrs = new StockWorkManagerImpl.LTRemain();
        String t_barcode = lt.getBarcode();
        if(!StringUtil.isNull(t_barcode)) {
            String remainSinworkUuid_sql = "select lt.location_task_uuid,rs.in_stock_work_uuid,rs.in_logistics_order_detail_uuid,rs.lot_code  from location_task lt,stock_work sw,remain_sinwork rs  where lt.location_task_uuid=sw.location_task_uuid  and sw.stock_work_uuid=rs.in_stock_work_uuid  and rs.barcode=\'" + t_barcode + "\' " + " and lt.loc_task_type<>\'PICK\' " + " and lt.office_code=\'" + lt.getOfficeCode() + "\' ";
            List nativesqlresult = this.nativeSqlDao.find(remainSinworkUuid_sql, new Object[0]);
            if(nativesqlresult != null && nativesqlresult.size() > 0) {
                result_ltrs.setLocationTaskUuid((String)((Object[])nativesqlresult.get(0))[0]);
                result_ltrs.setInStockWorkUuid((String)((Object[])nativesqlresult.get(0))[1]);
                result_ltrs.setInLogisticsOrderDetailUuid((String)((Object[])nativesqlresult.get(0))[2]);
                result_ltrs.setLotCode((String)((Object[])nativesqlresult.get(0))[3]);
            } else {
                String t_item_code = lt.getItemCode();
                if(!StringUtil.isNull(t_item_code)) {
                    String t_lotCode_sql = "select imlt.loc_type_code  from item_master im,item_master_loc_type imlt  where im.item_master_uuid = imlt.item_master_uuid   and im.item_code=\'" + t_item_code + "\' " + " and imlt.loc_type_code is not null " + " and rownum=1";
                    String t_lotCode = this.sqlQueryManager.getColumnData(t_lotCode_sql, "", "");
                    if(!StringUtil.isNull(t_lotCode)) {
                        result_ltrs.setLotCode(t_lotCode);
                    }
                }
            }
        } else {
            result_ltrs = this.getNbHbRemainSinwork(lt);
        }

        return result_ltrs;
    }

    private StockWorkManagerImpl.LTRemain getNbHbRemainSinwork(LocationTaskModel lt) {
        StockWorkManagerImpl.LTRemain ltsw = new StockWorkManagerImpl.LTRemain();
        String str_sql = "select lt.location_task_uuid,rs.in_stock_work_uuid,rs.in_logistics_order_detail_uuid,rs.lot_code  from location_task lt,stock_work sw,remain_sinwork rs  where rs.in_logistics_order_detail_uuid=\'" + lt.getInLogisticsOrderDetailUuid() + "\' " + " and nvl(rs.batch_no,\' \')=? " + " and nvl(rs.item_code,\' \')=? " + " and nvl(rs.marks_number,\' \')=? " + " and nvl(rs.goods_nature,\' \')=? " + " and nvl(rs.goods_kind,\' \')=? " + " and nvl(rs.instock_unit_code,\' \')=? " + " and nvl(rs.PANEL_NO,\' \')=? " + " and nvl(rs.PACKAGE_NO,\' \')=? " + " and nvl(rs.BARCODE,\' \')=? " + " and rs.production_date=? " + " and rs.office_code=\'" + lt.getOfficeCode() + "\' " + " and lt.location_task_uuid=sw.location_task_uuid " + " and sw.stock_work_uuid=rs.in_stock_work_uuid " + " and lt.loc_task_type<>\'PICK\' ";
        if(RcUtil.isEmpty(lt.getProductionDate())) {
            str_sql = "select lt.location_task_uuid,rs.in_stock_work_uuid,rs.in_logistics_order_detail_uuid,rs.lot_code   from location_task lt,stock_work sw,remain_sinwork rs  where rs.in_logistics_order_detail_uuid=\'" + lt.getInLogisticsOrderDetailUuid() + "\' " + " and nvl(rs.batch_no,\' \')=? " + " and nvl(rs.item_code,\' \')=? " + " and nvl(rs.marks_number,\' \')=? " + " and nvl(rs.goods_nature,\' \')=? " + " and nvl(rs.goods_kind,\' \')=? " + " and nvl(rs.instock_unit_code,\' \')=? " + " and nvl(rs.PANEL_NO,\' \')=? " + " and nvl(rs.PACKAGE_NO,\' \')=? " + " and nvl(rs.BARCODE,\' \')=? " + " and rs.office_code=\'" + lt.getOfficeCode() + "\' " + " and lt.location_task_uuid=sw.location_task_uuid " + " and sw.stock_work_uuid=rs.in_stock_work_uuid " + " and lt.loc_task_type<>\'PICK\' ";
        }

        try {
            new ArrayList();
            List e;
            if(RcUtil.isEmpty(lt.getProductionDate())) {
                e = this.nativeSqlDao.find(str_sql, new Object[]{this.SqlToNull(lt.getBatchNo()), this.SqlToNull(lt.getItemCode()), this.SqlToNull(lt.getMarksNumber()), this.SqlToNull(lt.getGoodsNature()), this.SqlToNull(lt.getGoodsKind()), this.SqlToNull(lt.getUnitCode()), this.SqlToNull(lt.getPanelNo()), this.SqlToNull(lt.getPackageNo()), this.SqlToNull(lt.getBarcode())});
            } else {
                e = this.nativeSqlDao.find(str_sql, new Object[]{this.SqlToNull(lt.getBatchNo()), this.SqlToNull(lt.getItemCode()), this.SqlToNull(lt.getMarksNumber()), this.SqlToNull(lt.getGoodsNature()), this.SqlToNull(lt.getGoodsKind()), this.SqlToNull(lt.getUnitCode()), this.SqlToNull(lt.getPanelNo()), this.SqlToNull(lt.getPackageNo()), this.SqlToNull(lt.getBarcode()), lt.getProductionDate()});
            }

            if(e != null && e.size() > 0) {
                ltsw.setLocationTaskUuid((String)((Object[])e.get(0))[0]);
                ltsw.setInStockWorkUuid((String)((Object[])e.get(0))[1]);
                ltsw.setInLogisticsOrderDetailUuid((String)((Object[])e.get(0))[2]);
                ltsw.setLotCode((String)((Object[])e.get(0))[3]);
            }
        } catch (Exception var5) {
            this.log.error(var5, var5);
        }

        return ltsw;
    }

    public boolean DelSPU(String LodUuid) {
        boolean result = false;
        if(StringUtil.isNull(LodUuid)) {
            throw new ApplicationException("LodUuid为空！");
        } else {
            String checkRs_sql = "select rs.remain_sinwork_uuid from location_task lt,stock_work sw,remain_sinwork rs  where lt.location_task_uuid=sw.location_task_uuid  and sw.stock_work_uuid=rs.in_stock_work_uuid  and lt.logistics_order_detail_uuid=\'" + LodUuid + "\' " + " and rs.remain_qty>0 ";
            String checkRs_bz = this.sqlQueryManager.getColumnData(checkRs_sql, "", "");
            if(!StringUtil.isNull(checkRs_bz)) {
                throw new ApplicationException("有库存信息，无法删除！");
            } else {
                List ls_lt = this.dao.createCommonQuery(LocationTaskModel.class).addCondition(Condition.eq("logisticsOrderDetailUuid", LodUuid)).query();
                if(ls_lt != null && ls_lt.size() > 0) {
                    for(int i = 0; i < ls_lt.size(); ++i) {
                        LocationTaskModel dellt = (LocationTaskModel)ls_lt.get(i);
                        String LtUuid = dellt.getLocationTaskUuid();
                        if(!StringUtil.isNull(LtUuid)) {
                            List ls_sw = this.dao.createCommonQuery(StockWorkModel.class).addCondition(Condition.eq("locationTaskUuid", LtUuid)).query();
                            if(ls_sw != null && ls_sw.size() > 0) {
                                this.dao.removeAll(ls_sw);
                            }
                        }

                        this.dao.remove(dellt);
                    }
                }

                result = true;
                return result;
            }
        }
    }

    private double getLodDeliveredQty(String lodUuid, String as_type) {
        double deli_qty = 0.0D;
        if(StringUtil.isNull(lodUuid)) {
            throw new ApplicationException("lodUuid为空");
        } else if(StringUtil.isNull(as_type)) {
            throw new ApplicationException("TRANSACTION_TYPE为空");
        } else {
            String str_sql = "select to_char(nvl(sum(lod.delivered_qty),0)) from logistics_order_detail lod  where lod.logistics_order_uuid=\'" + lodUuid + "\' ";
            if(as_type.equals("PDN")) {
                str_sql = "select nvl(sum(lod.delivered_qty),0) from logistics_order_detail lod  where lod.logistics_order_uuid=\'" + lodUuid + "\' and substr(lod.control_word,1,1)=\'S\' ";
            }

            String as_qty = this.sqlQueryManager.getColumnData(str_sql, "", "");
            if(StringUtil.isNull(as_qty)) {
                as_qty = "0";
            }

            deli_qty = StringUtil.doubleTo0(StringUtil.StringToDouble(as_qty, 0));
            return deli_qty;
        }
    }

    private double getLodConfirmedQty(String lodUuid) {
        double conf_qty = 0.0D;
        if(StringUtil.isNull(lodUuid)) {
            throw new ApplicationException("lodUuid为空");
        } else {
            String str_sql = "select to_char(nvl(sum(lod.Confirmed_Qty),0)) from logistics_order_detail lod  where lod.logistics_order_uuid=\'" + lodUuid + "\' ";
            String as_qty = this.sqlQueryManager.getColumnData(str_sql, "", "");
            if(StringUtil.isNull(as_qty)) {
                as_qty = "0";
            }

            conf_qty = StringUtil.doubleTo0(StringUtil.StringToDouble(as_qty, 0));
            return conf_qty;
        }
    }

    private boolean checkLtPanelNo(LocationTaskModel lt) {
        String officeCode = lt.getOfficeCode();
        String panelNo = lt.getPanelNo();
        return this.checkPanelNo(panelNo, officeCode);
    }

    public boolean checkPanelNo(String panelNo, String officeCode) {
        boolean result = true;
        if(StringUtil.isNull(officeCode)) {
            throw new ApplicationException("officeCode为空！");
        } else {
            if(!StringUtil.isNull(panelNo)) {
                String str_sql = "select lpc.loc_plan_config_uuid from remain_sinwork rs, logistics_order_detail lod,logistics_order lo,loc_plan_config lpc  where lod.logistics_order_uuid=lo.logistics_order_uuid  and lod.logistics_order_detail_uuid=rs.in_logistics_order_detail_uuid  and lpc.customer_code=lo.agent_consignee_code  and lpc.office_code=lo.office_code  and lpc.config_type=\'PALT\'  and lpc.status=\'Active\'  and rs.panel_no=\'" + panelNo + "\'   " + " and rs.office_code=\'" + officeCode + "\' " + " order by substr(lpc.control_word,1,1) desc ";
                String lpc_uuid = this.sqlQueryManager.getColumnData(str_sql, "", "");
                if(!StringUtil.isNull(lpc_uuid)) {
                   // String lpc_sql = this.locPlanConfigManager.getStrategyConditionSql(lpc_uuid);
                	String lpc_sql =null;
                    if(!StringUtil.isNull(lpc_sql)) {
                        String str_sql2 = "select rs.remain_sinwork_uuid from remain_sinwork rs  where rs.panel_no=\'" + panelNo + "\' " + " and rs.office_code=\'" + officeCode + "\' and " + lpc_sql;
                        String rs_uuid = this.sqlQueryManager.getColumnData(str_sql2, "", "");
                        if(!StringUtil.isNull(rs_uuid)) {
                            result = false;
                            throw new ApplicationException("当前移托操作不符合移托策略！");
                        }
                    }
                }
            }

            return result;
        }
    }

    private String checkRemainHold(LocationTaskModel lt) {
        String IS_PACKAGE_SQL = "select trim(OCP.IS_CONTROL) from OPTION_CONTROL_PROPERTIES ocp,OPTION_CONTROL oc  where oc.option_control_uuid=ocp.option_control_uuid  and oc.oc_code=\'REMAIN_HOLD_CONTROL\'  and ocp.oc_pr_code=\'IS_PACKAGE_NO\'  and ocp.office_code=\'" + lt.getOfficeCode() + "\' and rownum=1 ";
        String IS_PACKAGE_NO = this.sqlQueryManager.getColumnData(IS_PACKAGE_SQL, "", "");
        String rhUuid = "";
        String str_sql = "select rh.remain_hold_uuid  from remain_hold rh  where rh.in_logistics_order_detail_uuid=\'" + lt.getInLogisticsOrderDetailUuid() + "\' " + " and ifnull(rh.batch_no,\' \')=? " + " and ifnull(rh.item_code,\' \')=? " + " and ifnull(rh.marks_number,\' \')=? " + " and ifnull(rh.goods_nature,\' \')=? " + " and ifnull(rh.goods_kind,\' \')=? " + " and ifnull(rh.instock_unit_code,\' \')=? " + " and rh.production_date=? " + " and rh.office_code=\'" + lt.getOfficeCode() + "\' ";
        if(RcUtil.isEmpty(lt.getProductionDate())) {
            str_sql = "select rh.remain_hold_uuid  from remain_hold rh  where rh.in_logistics_order_detail_uuid=\'" + lt.getInLogisticsOrderDetailUuid() + "\' " + " and ifnull(rh.batch_no,\' \')=? " + " and ifnull(rh.item_code,\' \')=? " + " and ifnull(rh.marks_number,\' \')=? " + " and ifnull(rh.goods_nature,\' \')=? " + " and ifnull(rh.goods_kind,\' \')=? " + " and ifnull(rh.instock_unit_code,\' \')=? " + " and rh.office_code=\'" + lt.getOfficeCode() + "\' ";
        }

        if(IS_PACKAGE_NO.equals("Y")) {
            str_sql = str_sql + " and nvl(rh.package_no,\' \')=? ";
        }

        try {
            new ArrayList();
            List e;
            if(RcUtil.isEmpty(lt.getProductionDate())) {
                if(IS_PACKAGE_NO.equals("Y")) {
                    e = this.nativeSqlDao.find(str_sql, new Object[]{this.SqlToNull(lt.getBatchNo()), this.SqlToNull(lt.getItemCode()), this.SqlToNull(lt.getMarksNumber()), this.SqlToNull(lt.getGoodsNature()), this.SqlToNull(lt.getGoodsKind()), this.SqlToNull(lt.getUnitCode()), this.SqlToNull(lt.getPackageNo())});
                } else {
                    e = this.nativeSqlDao.find(str_sql, new Object[]{this.SqlToNull(lt.getBatchNo()), this.SqlToNull(lt.getItemCode()), this.SqlToNull(lt.getMarksNumber()), this.SqlToNull(lt.getGoodsNature()), this.SqlToNull(lt.getGoodsKind()), this.SqlToNull(lt.getUnitCode())});
                }
            } else if(IS_PACKAGE_NO.equals("Y")) {
                e = this.nativeSqlDao.find(str_sql, new Object[]{this.SqlToNull(lt.getBatchNo()), this.SqlToNull(lt.getItemCode()), this.SqlToNull(lt.getMarksNumber()), this.SqlToNull(lt.getGoodsNature()), this.SqlToNull(lt.getGoodsKind()), this.SqlToNull(lt.getUnitCode()), lt.getProductionDate(), this.SqlToNull(lt.getPackageNo())});
            } else {
                e = this.nativeSqlDao.find(str_sql, new Object[]{this.SqlToNull(lt.getBatchNo()), this.SqlToNull(lt.getItemCode()), this.SqlToNull(lt.getMarksNumber()), this.SqlToNull(lt.getGoodsNature()), this.SqlToNull(lt.getGoodsKind()), this.SqlToNull(lt.getUnitCode()), lt.getProductionDate()});
            }

            if(e != null && e.size() > 0) {
                rhUuid = (String)((Object[])e.get(0))[0];
            }
        } catch (Exception var7) {
            this.log.error(var7, var7);
        }

        return rhUuid;
    }

    private String getRStoRemainHold(RemainSinworkModel rs) {
        String IS_PACKAGE_SQL = "select trim(OCP.IS_CONTROL) from OPTION_CONTROL_PROPERTIES ocp,OPTION_CONTROL oc  where oc.option_control_uuid=ocp.option_control_uuid  and oc.oc_code=\'REMAIN_HOLD_CONTROL\'  and ocp.oc_pr_code=\'IS_PACKAGE_NO\'  and ocp.office_code=\'" + rs.getOfficeCode() + "\' and rownum=1 ";
        String IS_PACKAGE_NO = this.sqlQueryManager.getColumnData(IS_PACKAGE_SQL, "", "");
        String rhUuid = "";
        String str_sql = "select rh.remain_hold_uuid  from remain_hold rh  where rh.in_logistics_order_detail_uuid=\'" + rs.getInLogisticsOrderDetailUuid() + "\' " + " and nvl(rh.batch_no,\' \')=? " + " and nvl(rh.item_code,\' \')=? " + " and nvl(rh.marks_number,\' \')=? " + " and nvl(rh.goods_nature,\' \')=? " + " and nvl(rh.goods_kind,\' \')=? " + " and nvl(rh.instock_unit_code,\' \')=? " + " and rh.production_date=? " + " and rh.office_code=\'" + rs.getOfficeCode() + "\' ";
        if(RcUtil.isEmpty(rs.getProductionDate())) {
            str_sql = "select rh.remain_hold_uuid  from remain_hold rh  where rh.in_logistics_order_detail_uuid=\'" + rs.getInLogisticsOrderDetailUuid() + "\' " + " and nvl(rh.batch_no,\' \')=? " + " and nvl(rh.item_code,\' \')=? " + " and nvl(rh.marks_number,\' \')=? " + " and nvl(rh.goods_nature,\' \')=? " + " and nvl(rh.goods_kind,\' \')=? " + " and nvl(rh.instock_unit_code,\' \')=? " + " and rh.office_code=\'" + rs.getOfficeCode() + "\' ";
        }

        if(IS_PACKAGE_NO.equals("Y")) {
            str_sql = str_sql + " and nvl(rh.package_no,\' \')=? ";
        }

        try {
            new ArrayList();
            List e;
            if(RcUtil.isEmpty(rs.getProductionDate())) {
                if(IS_PACKAGE_NO.equals("Y")) {
                    e = this.nativeSqlDao.find(str_sql, new Object[]{this.SqlToNull(rs.getBatchNo()), this.SqlToNull(rs.getItemCode()), this.SqlToNull(rs.getMarksNumber()), this.SqlToNull(rs.getGoodsNature()), this.SqlToNull(rs.getGoodsKind()), this.SqlToNull(rs.getInstockUnitCode()), this.SqlToNull(rs.getPackageNo())});
                } else {
                    e = this.nativeSqlDao.find(str_sql, new Object[]{this.SqlToNull(rs.getBatchNo()), this.SqlToNull(rs.getItemCode()), this.SqlToNull(rs.getMarksNumber()), this.SqlToNull(rs.getGoodsNature()), this.SqlToNull(rs.getGoodsKind()), this.SqlToNull(rs.getInstockUnitCode())});
                }
            } else if(IS_PACKAGE_NO.equals("Y")) {
                e = this.nativeSqlDao.find(str_sql, new Object[]{this.SqlToNull(rs.getBatchNo()), this.SqlToNull(rs.getItemCode()), this.SqlToNull(rs.getMarksNumber()), this.SqlToNull(rs.getGoodsNature()), this.SqlToNull(rs.getGoodsKind()), this.SqlToNull(rs.getInstockUnitCode()), rs.getProductionDate(), this.SqlToNull(rs.getPackageNo())});
            } else {
                e = this.nativeSqlDao.find(str_sql, new Object[]{this.SqlToNull(rs.getBatchNo()), this.SqlToNull(rs.getItemCode()), this.SqlToNull(rs.getMarksNumber()), this.SqlToNull(rs.getGoodsNature()), this.SqlToNull(rs.getGoodsKind()), this.SqlToNull(rs.getInstockUnitCode()), rs.getProductionDate()});
            }

            if(e != null && e.size() > 0) {
                rhUuid = (String)((Object[])e.get(0))[0];
            }
        } catch (Exception var7) {
            this.log.error(var7, var7);
        }

        return rhUuid;
    }

    private String checkRemainSinwork(LocationTaskModel lt) {
        String InStockWorkUuid = "";
        String str_sql = "select rs.in_stock_work_uuid  from remain_sinwork rs  where rs.in_logistics_order_detail_uuid=\'" + lt.getInLogisticsOrderDetailUuid() + "\' " + " and nvl(rs.batch_no,\' \')=? " + " and nvl(rs.item_code,\' \')=? " + " and nvl(rs.marks_number,\' \')=? " + " and nvl(rs.goods_nature,\' \')=? " + " and nvl(rs.goods_kind,\' \')=? " + " and nvl(rs.instock_unit_code,\' \')=? " + " and nvl(rs.PANEL_NO,\' \')=? " + " and nvl(rs.PACKAGE_NO,\' \')=? " + " and nvl(rs.BARCODE,\' \')=? " + " and nvl(rs.LOT_CODE,\' \')=? " + " and rs.production_date=? " + " and rs.office_code=\'" + lt.getOfficeCode() + "\' ";
        if(RcUtil.isEmpty(lt.getProductionDate())) {
            str_sql = "select rs.in_stock_work_uuid  from remain_sinwork rs  where rs.in_logistics_order_detail_uuid=\'" + lt.getInLogisticsOrderDetailUuid() + "\' " + " and nvl(rs.batch_no,\' \')=? " + " and nvl(rs.item_code,\' \')=? " + " and nvl(rs.marks_number,\' \')=? " + " and nvl(rs.goods_nature,\' \')=? " + " and nvl(rs.goods_kind,\' \')=? " + " and nvl(rs.instock_unit_code,\' \')=? " + " and nvl(rs.PANEL_NO,\' \')=? " + " and nvl(rs.PACKAGE_NO,\' \')=? " + " and nvl(rs.BARCODE,\' \')=? " + " and nvl(rs.LOT_CODE,\' \')=? " + " and rs.office_code=\'" + lt.getOfficeCode() + "\' ";
        }

        try {
            new ArrayList();
            List e;
            if(RcUtil.isEmpty(lt.getProductionDate())) {
                e = this.nativeSqlDao.find(str_sql, new Object[]{this.SqlToNull(lt.getBatchNo()), this.SqlToNull(lt.getItemCode()), this.SqlToNull(lt.getMarksNumber()), this.SqlToNull(lt.getGoodsNature()), this.SqlToNull(lt.getGoodsKind()), this.SqlToNull(lt.getUnitCode()), this.SqlToNull(lt.getPanelNo()), this.SqlToNull(lt.getPackageNo()), this.SqlToNull(lt.getBarcode()), this.SqlToNull(lt.getTargetLotCode())});
            } else {
                e = this.nativeSqlDao.find(str_sql, new Object[]{this.SqlToNull(lt.getBatchNo()), this.SqlToNull(lt.getItemCode()), this.SqlToNull(lt.getMarksNumber()), this.SqlToNull(lt.getGoodsNature()), this.SqlToNull(lt.getGoodsKind()), this.SqlToNull(lt.getUnitCode()), this.SqlToNull(lt.getPanelNo()), this.SqlToNull(lt.getPackageNo()), this.SqlToNull(lt.getBarcode()), this.SqlToNull(lt.getTargetLotCode()), lt.getProductionDate()});
            }

            if(e != null && e.size() > 0) {
                InStockWorkUuid = (String)((Object[])e.get(0))[0];
            }
        } catch (Exception var5) {
            this.log.error(var5, var5);
        }

        return InStockWorkUuid;
    }

    private boolean checkinStockWorkUuid(LocationTaskModel lt) {
        boolean result = true;
        if(StringUtil.isNull(lt.getInStockWorkUuid())) {
            result = false;
            throw new ApplicationException("LT记录的InStockWorkUuid为空");
        } else {
            if(!StringUtil.isNull(lt.getBarcode())) {
                String rs_sql = "select RS.in_stock_work_uuid from location_task lt,stock_work sw,remain_sinwork rs  where lt.location_task_uuid=sw.location_task_uuid  and sw.stock_work_uuid=rs.in_stock_work_uuid  and rs.barcode=\'" + lt.getBarcode() + "\' " + " and lt.loc_task_type<>\'PICK\' " + " and lt.office_code=\'" + lt.getOfficeCode() + "\' AND ROWNUM=1 ";
                String inStockWorkUuid = this.sqlQueryManager.getColumnData(rs_sql, "", "");
                if(StringUtil.isNull(inStockWorkUuid)) {
                    throw new ApplicationException("LT记录的【" + lt.getBarcode() + "】没有检索到库存数据");
                }

                if(!inStockWorkUuid.equals(lt.getInStockWorkUuid())) {
                    result = false;
                    throw new ApplicationException("LT记录的InStockWorkUuid与库存记录不符，请检查是否选择了拣货的库存记录");
                }
            }

            return result;
        }
    }

    private RemainSinworkModel getInsRemainSinworkUuid(LocationTaskModel lt) {
        RemainSinworkModel new_rs = new RemainSinworkModel();
        if(!StringUtil.isNull(lt.getBarcode())) {
            String remainSinworkUuid_sql = "select RS.REMAIN_SINWORK_UUID from location_task lt,stock_work sw,remain_sinwork rs  where lt.location_task_uuid=sw.location_task_uuid  and sw.stock_work_uuid=rs.in_stock_work_uuid  and rs.barcode=\'" + lt.getBarcode() + "\' " + " and lt.loc_task_type<>\'PICK\' " + " and lt.office_code=\'" + lt.getOfficeCode() + "\' AND ROWNUM=1 ";
            String remainSinworkUuid = this.sqlQueryManager.getColumnData(remainSinworkUuid_sql, "", "");
            if(!StringUtil.isNull(remainSinworkUuid)) {
                List l_rs = this.dao.createCommonQuery(RemainSinworkModel.class).addCondition(Condition.eq("remainSinworkUuid", remainSinworkUuid)).addCondition(Condition.eq("inLogisticsOrderDetailUuid", lt.getInLogisticsOrderDetailUuid())).addDynamicCondition(Condition.eq("batchNo", lt.getBatchNo())).addDynamicCondition(Condition.eq("itemCode", lt.getItemCode())).addDynamicCondition(Condition.eq("packageNo", lt.getPackageNo())).addDynamicCondition(Condition.eq("marksNumber", lt.getMarksNumber())).addDynamicCondition(Condition.eq("goodsNature", lt.getGoodsNature())).addDynamicCondition(Condition.eq("goodsKind", lt.getGoodsKind())).addDynamicCondition(Condition.eq("instockSecondUnitCode", lt.getUnitCode())).addDynamicCondition(Condition.eq("productionDate", lt.getProductionDate())).addCondition(Condition.eq("barcode", lt.getBarcode())).addCondition(Condition.eq("officeCode", lt.getOfficeCode())).query();
                if(l_rs != null && l_rs.size() > 0) {
                    new_rs = (RemainSinworkModel)l_rs.get(0);
                }
            }
        }

        return new_rs;
    }

    private String SqlToNull(String para) {
        if(StringUtil.isNull(para)) {
            para = " ";
        }

        return para;
    }

    private String checkRemainHoldQty(LocationTaskModel lt) {
        String rhqty = "";
        String IS_PACKAGE_SQL = "select trim(OCP.IS_CONTROL) from OPTION_CONTROL_PROPERTIES ocp,OPTION_CONTROL oc  where oc.option_control_uuid=ocp.option_control_uuid  and oc.oc_code=\'REMAIN_HOLD_CONTROL\'  and ocp.oc_pr_code=\'IS_PACKAGE_NO\'  and ocp.office_code=\'" + lt.getOfficeCode() + "\' and rownum=1 ";
        String IS_PACKAGE_NO = this.sqlQueryManager.getColumnData(IS_PACKAGE_SQL, "", "");
        String PACKAGE_NO_sql = " ";
        if(IS_PACKAGE_NO.equals("Y")) {
            PACKAGE_NO_sql = " and nvl(rh.package_no,\' \')=nvl(rs.package_no,\' \') ";
        }

        String str_sql = "select to_char(nvl(rh.remain_qty,0) - nvl(rh.hold_qty,0))  from remain_hold rh  where rh.in_logistics_order_detail_uuid=\'" + lt.getInLogisticsOrderDetailUuid() + "\' " + " and exists  (select \'x\' from remain_sinwork rs  " + " where nvl(rh.batch_no,\' \')=nvl(rs.batch_no,\' \') " + " and nvl(rh.item_code,\' \')=nvl(rs.item_code,\' \') " + PACKAGE_NO_sql + " and nvl(rh.marks_number,\' \')=nvl(rs.marks_number,\' \') " + " and nvl(rh.goods_nature,\' \')=nvl(rs.goods_nature,\' \') " + " and nvl(rh.goods_kind,\' \')=nvl(rs.goods_kind,\' \') " + " and nvl(rh.instock_unit_code,\' \')=nvl(rs.instock_unit_code,\' \') " + " and nvl(rh.production_date,to_date(\'2000-01-01\',\'yyyy-mm-dd\')) = nvl(rs.production_date,to_date(\'2000-01-01\',\'yyyy-mm-dd\')) " + " and rh.office_code=rs.office_code " + " and rs.in_stock_work_uuid=\'" + lt.getInStockWorkUuid() + "\' ) " + " and rh.office_code=\'" + lt.getOfficeCode() + "\' " + " and nvl(rh.remain_qty,0) - nvl(rh.hold_qty,0) >=  " + lt.getQty();
        rhqty = this.sqlQueryManager.getColumnData(str_sql, "", "");
        return rhqty;
    }

    public class LTRemain {
        private String locationTaskUuid;
        private String remainSinworkUuid;
        private String inLogisticsOrderDetailUuid;
        private String lotCode;
        private String inStockWorkUuid;

        public LTRemain() {
        }

        public String getLocationTaskUuid() {
            return this.locationTaskUuid;
        }

        public void setLocationTaskUuid(String locationTaskUuid) {
            this.locationTaskUuid = locationTaskUuid;
        }

        public String getInLogisticsOrderDetailUuid() {
            return this.inLogisticsOrderDetailUuid;
        }

        public void setInLogisticsOrderDetailUuid(String inLogisticsOrderDetailUuid) {
            this.inLogisticsOrderDetailUuid = inLogisticsOrderDetailUuid;
        }

        public String getLotCode() {
            return this.lotCode;
        }

        public void setLotCode(String lotCode) {
            this.lotCode = lotCode;
        }

        public String getRemainSinworkUuid() {
            return this.remainSinworkUuid;
        }

        public void setRemainSinworkUuid(String remainSinworkUuid) {
            this.remainSinworkUuid = remainSinworkUuid;
        }

        public String getInStockWorkUuid() {
            return this.inStockWorkUuid;
        }

        public void setInStockWorkUuid(String inStockWorkUuid) {
            this.inStockWorkUuid = inStockWorkUuid;
        }
    }
}
