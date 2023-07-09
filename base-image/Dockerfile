FROM raspbian/stretch as unzipper
COPY ./jdk11-linux-arm32.tar.gz_aa /
COPY ./jdk11-linux-arm32.tar.gz_ab /
RUN cat ./jdk11-linux-arm32.tar.gz_?? > ./jdk11-linux-arm32.tar.gz
RUN mkdir /java
RUN tar -xvzf ./jdk11-linux-arm32.tar.gz -C /java

FROM raspbian/stretch
WORKDIR /
COPY --from=unzipper /java /java
ENV JAVA_HOME /java
ENV PATH $JAVA_HOME/bin:$PATH
RUN apt update && \
    apt upgrade -y && \
    ln -s /java/bin/java /usr/bin/java && \
    ln -s /java/bin/javac /usr/bin/javac
