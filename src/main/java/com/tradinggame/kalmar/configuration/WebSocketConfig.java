package com.tradinggame.kalmar.configuration;


import org.springframework.context.annotation.Configuration;
        import org.springframework.messaging.simp.config.MessageBrokerRegistry;
        import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
        import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
        import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //letrehoz egy endpointot
        registry.addEndpoint("/game_lobby")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //ez allitja be h a /topic utvonalra fel lehet iratkozni, itt kapja meg az uzeneteket
        registry.enableSimpleBroker("/topic");
        //ez allitja be h milyen utvonal prefixel kezdodik ahova tudsz kuldeni
        registry.setApplicationDestinationPrefixes("/app");
    }

}
