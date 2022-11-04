FROM node:18-alpine
WORKDIR /var/www/dashboard

COPY ./dashboard/front/app/package*.json /var/www/dashboard/
COPY ./dashboard/front/app /var/www/dashboard

RUN npm install 
RUN npm run build
CMD [ "npm", "start" ]
USER node