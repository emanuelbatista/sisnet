package com.br.ifpb.daoFactory;

public class DaoFactory {
 
	public static DaoFactoryIF createFactory(){
		return new DaoFactoryBD();
	}
}
