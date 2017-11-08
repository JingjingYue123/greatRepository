package com.djcps.crm.commons.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by LK on 2017/6/26.
 */
public class DJGson {

	public static Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();

}