package whu.izumisakaizy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Izumi Sakai
 * @since 2020-11-26
 */
    public class Song implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String name;

    private String singer;

    private String feeling;

  public Song(Integer id, String name, String singer, String feeling) {
    this.id = id;
    this.name = name;
    this.singer = singer;
    this.feeling = feeling;
  }

  public Song() {
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSinger() {
    return singer;
  }

  public void setSinger(String singer) {
    this.singer = singer;
  }

  public String getFeeling() {
    return feeling;
  }

  public void setFeeling(String feeling) {
    this.feeling = feeling;
  }
}
