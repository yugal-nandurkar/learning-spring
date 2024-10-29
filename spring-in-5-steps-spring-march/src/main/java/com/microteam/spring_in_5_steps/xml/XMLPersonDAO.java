package com.microteam.spring_in_5_steps.xml;


public class XMLPersonDAO {

    XMLJdbcConnection xmlJdbcConnection;

    public XMLJdbcConnection getXMLJdbcConnection() {
        return xmlJdbcConnection;
    }

    public void setXMLJdbcConnection(XMLJdbcConnection jdbcConnection) {
        this.xmlJdbcConnection = jdbcConnection;
    }
}
