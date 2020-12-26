# Spring Integration: 
Spring Integration using Java configuration.

# Run
    Application URL: http://localhost:8080

## Docker setup for RabbitMQ
### Check docker version
    docker --version

### Download the RabbitMQ image 
In this case, we will download the image with a management plugin in order to be able to view the RabbitMQ server-side GUI.
   
    docker pull rabbitmq:3-management

### Start the RabbitMQ
    docker run -d -p 15672:15672 -p 5672:5672 --name spring-integration-rabbitmq rabbitmq:3-management

### RabbitMQ management GUI.
    http://localhost:15672/#/
_**The default login is guest:guest**_

