# get Node lts from docker hub
FROM node:18.17.1 AS build

# set working directory
WORKDIR /app

# copy package.json to workspace
COPY package*.json ./

# install pnpm
RUN npm install -g pnpm

# install dependencies
RUN pnpm install

# copy the rest of the files to the workspace
COPY . .


# build production version
RUN pnpm run build

# # install serve
# RUN npm install -g serve

# # run the app
# CMD ["serve","-l", "5173", "-s", "build"]



# Step 2: Serve the React application from Nginx
FROM nginx:alpine

# Copy build output from build stage
COPY --from=build /app/dist /usr/share/nginx/html

# expose port
EXPOSE 5173

# Expose port 80
EXPOSE 80

# Start Nginx and keep it from exiting
CMD ["nginx", "-g", "daemon off;"]