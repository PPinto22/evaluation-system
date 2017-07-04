FROM node:boron

# Create app directory
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

# Install app dependencies
ADD dist/ /usr/src/app/
ADD Server.js /usr/src/app/
ADD package.json /usr/src/app/

RUN npm install

EXPOSE 8084
CMD [ "node", "Server.js" ]