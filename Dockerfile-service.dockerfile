FROM eclipse-temurin:17-jammy

COPY ./target/portaldengue-0.0.1-SNAPSHOT.jar ./portaldengue-0.0.1-SNAPSHOT.jar

ENTRYPOINT [ "java", "-jar", "/portaldengue-0.0.1-SNAPSHOT.jar" ]