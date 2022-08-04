/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kinnara.kecakplugins;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.joget.apps.app.service.AppUtil;
import org.joget.apps.datalist.model.DataList;
import org.joget.apps.datalist.model.DataListColumn;
import org.joget.apps.datalist.model.DataListColumnFormatDefault;
import org.joget.commons.util.LogUtil;

import javax.annotation.Nonnull;

/**
 *
 * @author Ramdan
 */
public class DateFormatter extends DataListColumnFormatDefault{

    @Override
    public String getName() {
        return "Date Formatter";
    }

    @Override
    public String getVersion() {
        return getClass().getPackage().getImplementationVersion();
    }

    @Override
    public String getDescription() {
        return getClass().getPackage().getImplementationTitle();
    }

    @Override
    public String format(DataList dataList, DataListColumn column, Object row, Object value) {
       String formatFrom = getPropertyString("formatFrom");
       String formatTo = getPropertyString("formatTo");   
       String resultDate =String.valueOf(value);
      
        try {
           return  new SimpleDateFormat(formatTo).format(new SimpleDateFormat(formatFrom).parse(resultDate));
        } catch (ParseException ex) {
            //LogUtil.warn(getClassName() , ex.getMessage());
        }
               
        return  resultDate;
    }

    @Override
    public String getLabel() {
        return getName();
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public String getPropertyOptions() {
        return AppUtil.readPluginResource(getClass().getName(), "/properties/DateFormater.json", null, true, null);
    }

    @Override
    public String getSortAs(@Nonnull DataList dataList, @Nonnull DataListColumn column) {
        return "cast(? as timestamp)";
    }
}
