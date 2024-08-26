package me.avankziar.lt.paper.database;

import me.avankziar.lt.general.database.MysqlBaseHandler;
import me.avankziar.lt.paper.LT;

public class MysqlHandler extends MysqlBaseHandler
{	
	public MysqlHandler(LT plugin)
	{
		super(plugin.getLogger(), plugin.getMysqlSetup());
	}
}
