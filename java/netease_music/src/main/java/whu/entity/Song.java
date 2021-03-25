package whu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Izumi Sakai
 * @since 2021-03-25
 */
public class Song implements Serializable {
    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String singer;
    private String name;

    public Song(Integer id, String singer, String name) {
        this.id = id;
        this.singer = singer;
        this.name = name;
    }

    public Song() {
    }

    public void setId(Integer id) {
    this.id = id;
  }

  public void setSinger(String singer) {
    this.singer = singer;
  }

  public void setName(String name) {
    this.name = name;
  }

}
