FROM node:boron

# Install app dependencies
RUN apt-get install git
RUN npm install -g @angular/cli

# Create app directory
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

RUN git clone https://github.com/uNmasK95/EvaluationSystem.git

WORKDIR /usr/src/app/EvaluationSystem/EvaluationSystem-cli
RUN npm install

EXPOSE 4200
CMD [ "npm", "start"]