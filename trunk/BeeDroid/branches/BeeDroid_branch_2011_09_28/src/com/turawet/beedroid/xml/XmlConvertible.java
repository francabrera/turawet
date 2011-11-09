package com.turawet.beedroid.xml;

import java.io.IOException;


public interface XmlConvertible
{
	public void convertToXml(XmlConverter converter) throws IllegalArgumentException, IllegalStateException, IOException;
}
