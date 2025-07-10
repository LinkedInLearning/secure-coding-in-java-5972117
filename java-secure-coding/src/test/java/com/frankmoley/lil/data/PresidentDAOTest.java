package com.frankmoley.lil.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.frankmoley.lil.util.DatabaseUtil;


public class PresidentDAOTest {

  @BeforeAll
  public static void setUp() throws Exception {
    DatabaseUtil.loadFile("schema.sql");
    DatabaseUtil.loadFile("data.sql");
  }

  @Test
  public void getByLastName() {
    PresidentDAO dao = new PresidentDAO();
    List<President> results = dao.getByLastName("Washington");
    assertNotNull(results);
    assertEquals(1, results.size());
    assertEquals("George", results.get(0).getFirstName());
  }

  @Test
  public void getByLastName_Injections() {
    PresidentDAO dao = new PresidentDAO();
    List<President> results = dao.getByLastName("Trump' or '1' = '1");
    assertNotNull(results);
    assertEquals(0, results.size());
  }
}