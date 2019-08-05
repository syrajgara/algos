package com.shabs.design;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Class name Pen, BuilderPattern just prefix for example
 */
public class BuilderPatternPen {
  private static final Logger logger = LoggerFactory.getLogger(BuilderPatternPen.class);

  // should be enums, but just want to show how constructor looks with multiple String and the need for Builder
  private String color;
  private String type;
  private int size;

  public BuilderPatternPen() {
    // this public constructor added just because we have a non-static @Test method in this class
  }

  // this could be public but with params (String, String) !!
  private BuilderPatternPen(String color, String type, int size) {
    this.color = color;
    this.type = type;
    this.size = size;
  }

  @Override
  public String toString() {
    return color + type + size;
  }

  public class Builder {
    private String color;
    private String type;
    private int size;

    // constructor should have all mandatory parameters
    public Builder(String color) {
      logger.info(color);
      this.color = color;
    }

    // this are variation of setters.
    public Builder type(String type) {
      logger.info(type);
      this.type = type;
      return this;
    }

    public Builder size(int size) {
      logger.info(String.valueOf(size));
      this.size = size;
      return this;
    }

    public BuilderPatternPen build() {
      return new BuilderPatternPen(color, type, size);
    }
  }

  @Test
  public void buildPen() {
    String color = "RED";
    String type = "DRY_ERASE";
    int size = 1;

    String expected = color + type + size;

    Builder penBuilder = new BuilderPatternPen.Builder(color);
    penBuilder.type(type);
    penBuilder.size(size);

    BuilderPatternPen pen = penBuilder.build();

    // hamcrest validators
    assertThat(pen.toString(), is(expected));
  }
}
