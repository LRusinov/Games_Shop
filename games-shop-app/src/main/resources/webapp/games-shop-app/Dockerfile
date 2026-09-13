# Build Angular application
FROM node:22-alpine AS build

WORKDIR /app

COPY package*.json ./

RUN npm ci

COPY . .

RUN npx ng build --configuration production


# Serve Angular application with nginx
FROM nginx:alpine

COPY --from=build /app/dist/games-shop-app /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
