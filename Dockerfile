FROM ubuntu:16.04

#===============
#Java
#===============
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y  software-properties-common && \
    add-apt-repository ppa:webupd8team/java -y && \
    apt-get update && \
    echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    apt-get install -y oracle-java8-installer && \
    apt-get clean

#===============
#Maven
#===============
RUN apt-get install -y maven

#===============
#Xfvb, Firefox
#===============
# We need wget to download the custom version of Firefox, xvfb to have a virtual screen and Firefox so all necessary libraries are installed.
RUN apt-get install -y wget xvfb firefox \
    xfonts-100dpi \
    xfonts-75dpi \
    xfonts-scalable \
    xfonts-cyrillic

#===============
# Google Chrome
#===============
#RUN wget --no-verbose -O /tmp/google-chrome-stable_current_amd64.deb https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb \
#  && apk add -f '/src/google-chrome-stable_current_amd64.deb' \
#  && rm /tmp/google-chrome-stable_current_amd64.deb

#==================
# Chrome webdriver
#==================
#ENV CHROME_DRIVER_VERSION 2.21
#RUN wget --no-verbose -O /tmp/chromedriver_linux64.zip https://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip \
#  && rm -rf /opt/chromedriver \
#  && mkdir /opt/chromedriver \
#  && unzip /tmp/chromedriver_linux64.zip -d /opt/chromedriver \
#  && rm /tmp/chromedriver_linux64.zip \
#  && mv /opt/chromedriver /opt/chromedriver-$CHROME_DRIVER_VERSION \
#  && chmod 755 /opt/chromedriver-$CHROME_DRIVER_VERSION \
#  && ln -fs /opt/chromedriver-$CHROME_DRIVER_VERSION /usr/bin/chromedriver

COPY . /home/testatoo
WORKDIR /home/testatoo

CMD ["/home/testatoo/script.sh"]

#RUN mvn test