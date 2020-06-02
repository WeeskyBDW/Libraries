package fr.entasia.apis.nbt;

import javax.annotation.Nullable;
import java.lang.reflect.Method;

public class NBTComponent {

//	public static Method mapGetter;
//	public static Field mapProperty;
	public static Method fusion, setPreciseTag, setString, delString, getString, getList, setList, getCompound;

	protected Object rawnbt;

	@Deprecated
	public void setNBT(Object nbt){
		this.rawnbt = nbt;
	}

	@Deprecated
	public Object getRawNBT(){
		return rawnbt;
	}

	public String getNBT(){
		return rawnbt.toString();
	}

	protected NBTComponent(Object nbt){
		this.rawnbt = nbt;
	}

	public NBTComponent(String nbt){
		this.rawnbt = NBTer.rawParseNBT(nbt);
	}

	public NBTComponent(){
		this.rawnbt = NBTer.rawParseNBT("{}");
	}



	@Deprecated
	public void setCompound(NBTComponent toAdd) {
		fusion(toAdd);
	}

	public void setComponent(String key, NBTComponent toAdd) {
		try{
			setPreciseTag.invoke(rawnbt, key, toAdd.rawnbt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fusion(NBTComponent toAdd) {
		try{
			fusion.invoke(rawnbt, toAdd.rawnbt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void delKey(String key) {
		try{
			delString.invoke(rawnbt, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setKeyString(String key, Object value) {
		setKeyString(key, value.toString());
	}

	public void setKeyString(String key, String value) {
		try{
			setString.invoke(rawnbt, key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Nullable
	public String getKeyString(String key) {
		try{
			String a = (String) getString.invoke(rawnbt, key);
			if(a.equals(""))return null;
			return a;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



//	public void setList(String key) {
//		try{
//			mapGetter.invoke(mapProperty.get(rawnbt), key);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Nullable
//	public String getList(String key) {
//		try{
//			Object o = mapGetter.invoke(mapProperty.get(rawnbt), key);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}



	@Nullable
	public NBTComponent getComponent(String key) {
		try{
			return new NBTComponent(getCompound.invoke(rawnbt, key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String toString(){
		return getNBT();
	}
}
