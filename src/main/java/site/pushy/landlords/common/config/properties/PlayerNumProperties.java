package site.pushy.landlords.common.config.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Data
@ConfigurationProperties(prefix = "player")
@Service

public class PlayerNumProperties {
    public int playNum ;
}
