package whu.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import whu.constant.CommonResult;
import whu.entity.Song;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Izumi Sakai
 * @since 2021-03-25
 */
public interface ISongService extends IService<Song> {
}
