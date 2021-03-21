# 使用方法
  在springBoot项目中使用:
  1. 配置sms;
      ```
      import SMS;
      import SendSmsProfile;
      import lombok.Getter;
      import lombok.Setter;
      import org.springframework.boot.context.properties.ConfigurationProperties;
      import org.springframework.context.annotation.Bean;
      import org.springframework.context.annotation.Configuration;
      
      @Configuration
      @ConfigurationProperties(prefix = "ali.sms")
      @Setter
      @Getter
      public class SmsConfig {
      
          /**
           * 短信签名, 在验证码短信中, 如【恩捷科技】中的"恩捷科技";
           */
          private String signName;
      
          /**
           * 短信模板代号, 请在阿里云短信控制台获取;
           */
          private String templateCode;
      
          /**
           * 账户ID, 请在阿里云短信控制台获取;
           */
          private String AccessKeyId;
      
          /**
           * 加密秘钥, 请在阿里云短信控制台获取;
           */
          private String secret;
      
          @Bean
          public SMS sendSms() {
      
              SendSmsProfile profile = new SendSmsProfile();
              //使用set方法请按照顺序,否则可能报错,无法找到相应方法;
              profile.setSignName(signName)
                      .setTemplateCode(templateCode)
                      .setAccessKeyId(AccessKeyId)
                      .setSecret(secret);
      
              return new SMS(profile);
          }
      }
      ```
  2. 配置yml
      ```
      ali:
         sms:
           sign-name: 镜边服务网
           template-code: SMS_11978****
           access-key-id: LTAIHylCq*******
           secret: 0qIAuwDFKqZ2jzBzg0brs9d*******
     ```
  3. 发送短信
      ```
      import OutOfSizeException;
      import SMS;
      import Result;
      import org.springframework.beans.factory.annotation.Autowired;
      import org.springframework.beans.factory.annotation.Qualifier;
      import org.springframework.stereotype.Service;
      
      import java.util.List;
      import java.util.Map;
      
      @Service
      public class SendSmsService {
      
          /**
           * 尽量指定Bean name,因为在后期功能更新可能出现需要spring管理多个SMS对象
           */
          @Autowired
          @Qualifier("sendSms")
          private SMS sms;
      
          /**
           * 第一种:
           * 会自动生成6位数字验证码并且发送, 且会在result.vCode中返回;
           *
           * @param phoneNumber tel
           */
          public void sendSms(String phoneNumber) {
      
              Result result = sms.sendSms(phoneNumber);
      
          }
      
          /**
           * 第二种:
           * 由开发者生成验证码发送,tips: 其实可以填写任意数据, 数据会替换掉短信模板中${code}变量;
           *
           * @param phoneNumber tel
           */
          public void sendSms2(String phoneNumber) {
      
              String code = "生成随机验证码算法";
              Result result1 = sms.sendSms(phoneNumber, code);
      
          }
      
          /**
           * 第三种：
           * 除了发送验证码,还可以发送其他信息,比如用此方法,可以向多个手机号以相同短信模板发送同样的信息;
           *
           * @param phoneNumbers   tels
           * @param templateParams 短信模板中的变量名(k)及想要替换的值(v)；
           * @throws OutOfSizeException 当号码数量超过1000，抛出此异常
           */
          public void sendSms3(List<String> phoneNumbers, Map<String, String> templateParams) throws OutOfSizeException {
      
              Result result2 = sms.sendSms(phoneNumbers, templateParams);
      
          }
      
      }
     ```

注：想要在yml中有提示，请在idea中配置，配置方法如下(idea版本：2020.1.x)：
- File -> Settings -> Build,Execution,Deployment -> Compiler -> Annotation Processors;
- 勾选Enable annotation processing;
- pom.xml添加spring-boot-configuration-processor依赖;
- 如果修改了配置类，则需要build项目，修改才能在yml中正确提示;

# 错误码查询
- https://help.aliyun.com/document_detail/101346.html

# 引入方法 
  1. 按照.m2 中的settings.xml 修改本地maven配置文件
  2. pom中引入仓库
  
      ```
      <repositories>
          <repository>
              <id>nexus</id>
              <url>http://repository.ynejkj.com/repository/maven-public/</url>
              <releases>
                  <enabled>true</enabled>
              </releases>
              <snapshots>
                  <enabled>true</enabled>
              </snapshots>
          </repository>
      </repositories>
      ```
  3. pom中引入依赖
  
      ```
      <dependency>
          <groupId>com.ynejkj</groupId>
          <artifactId>java-sms-aliyun</artifactId>
          <version>${version}</version>
      </dependency>
      ```