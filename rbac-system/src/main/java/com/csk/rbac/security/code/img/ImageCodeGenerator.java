package com.csk.rbac.security.code.img;

import com.csk.rbac.common.properties.RbacProperties;
import com.csk.rbac.security.code.ValidateCode;
import com.csk.rbac.security.code.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @description:图像验证码生成器
 * @author: caishengkai
 * @time: 2019/12/30 19:02
 **/
@Service
public class ImageCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private RbacProperties rbacProperties;

    @Override
    public ValidateCode createCode() {
        int width = rbacProperties.getCode().getWidth();
        int height = rbacProperties.getCode().getHeight();
        int length = rbacProperties.getCode().getLength();
        int expireIn = rbacProperties.getCode().getExpireIn();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();
        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 38));
        g.setColor(getRandColor(150, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(250);
            int yl = random.nextInt(250);
            g.drawLine(x, y, x + xl, y + yl);
        }

        StringBuilder sRand = new StringBuilder();
        for (int i = 0; i < length; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand.append(rand);
            g.setColor(new Color(10 + random.nextInt(110), 10 + random.nextInt(110), 10 + random.nextInt(110)));
            g.drawString(rand, 18 * i + 12, 32);
        }
        g.dispose();
        return new ImageCode(image, sRand.toString(), expireIn);
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
