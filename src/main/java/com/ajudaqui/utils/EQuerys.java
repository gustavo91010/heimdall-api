package com.ajudaqui.utils;

public enum EQuerys {

  SELECT_BY_API_KEY("SELECT * FROM application WHERE apiKey = :apiKey AND ROWNUM = 1"),
  TRUNCATE_ATE_FILT("truncate table ${schema}.ATE_FILTRO"),
  TRUNCATE_ATE_FILRO("truncate table ${schema}.ATE_FILTRO"),
  TRUNCATE_ATE_FIO("truncate table ${schema}.ATE_FILTRO");

  private final String sql;

  EQuerys(String sql) {
    this.sql = sql;
  }

  public String getSql() {
    return sql;
  }
}
