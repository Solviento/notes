# Deploying a web app using AWS and Node

Run Through Setting up AWS (if not run before, or setting up new machine)
- Click on Services, EC2 (Elastic Computing Cloud)
- Choose an Amazon Machine Image (Windows or Linux Distro)
- Choose free version
- Keep clicking continue and configure additional details
- Name the security group (this enables which ports should expose to which IP addresses)
- If testing a web app, we need to expose HTTP port 80 by adding a Rule to expose port 80 to all IPs
- Launch instance, once prompted to setup SSH key choose Create a new key pair
- Once the key pair is downloaded, this will allow you to login to the server (keep it safe!!)

Logging into the EC2 Server
- Make sure to place the key somewhere safe and hidden like ~/.ssh
- Remember to set: chmod 400 ~/.ssh/name-of-key.pem
- Get the username and address from AWS (right click on instance and click Connect)
  - Copy the bash command under Example (ex: ssh -i "~/.ssh/xyz.pem" ubuntu@name-of-aws.amazonaws.com)

Setting up node and system dependencies (if not setup before)
- Need to install NVM (Node version manager to install and switch Node version if needed)
- Update bashrc right after
- Then install node version 7

``` bash
curl -o- https://raw.githubusercontent.com/creationix/nvm/v0.32.1/install.sh | bash

source ~/.bashrc

nvm install 7
```

Setup npm
- cd into the folder you would like to deploy your files in
- Initialize npm and install express
- Write an index.js
- Do this only to test the instance and the node setup!!

``` bash
npm init

npm install express --save-dev

vim index.js
```

``` JavaScript
const express = require('express')
const app = express()
app.get('/', (req, res) => {
  res.send('HEY!')
})
// Change port number if needed, also append :3000 to the end of the IP address in browser
app.listen(3000, () => console.log('Server running on port 3000'))
```

``` bash
node index.js
```

- If using a custom port, remember to go the Security Groups tab in EC2 console
- Click edit inbound rules and Add Rule
- Add the port number and select source to anywhere (in most cases)
- Connect browser and test if working

Quick background services tips
- You can quickly allow node to run in the background

``` bash
node index.js

$ ctrl-z

$ [1]+ Stopped node index.js

bg %1
```

Setting up nginx on the EC2 Server
- Log into server
- Install nginx
- Connect to IP address without any port

``` bash
sudo apt-get install nginx

# run below if site is not working
sudo /etc/init.d/nginx start
```

- We now need to remove the default configuration files to use the custom port setup for our web app
- Then create a new config file to pass port 80 traffic to port 3000 (or any port)
- After we need to link the config files in sites-enabled and sites-available
- Restart service and display it running

``` bash
sudo rm /etc/nginx/sites-enabled/default

sudo vimv /etc/nginx/sites-available/tutorial

# Change port number if needed, also change server name
server {
  listen 80;
  server_name tutorial;
  location / {
    proxy_set_header  X-Real-IP  $remote_addr;
    proxy_set_header  Host       $http_host;
    proxy_pass        http://127.0.0.1:3000;
  }
}

sudo service nginx restart

jobs

# If serice is not running, execute then ctrl-z and run in the background
node tutorial/index.js

# Once finished, run
killall -9 node
```

Keeping node processes running without re-doing the entire process
- We install PM2 on the server and then execute

``` bash
npm i -g pm2

pm2 start tutorial/index.js --name "index"

# To make sure pm2 restarts when server starts
pm2 restart
# Save current responses when PM2 restarts
pm2 save
# To list all pm2 processes use
pm2 ls
# To stop, delete, and rename a process (originally labeled 'index') use
pm2 stop index

pm2 delete index

pm2 start tutorial/index.js --name "Some-new-name"
```

Deploying code into server
- Once the above steps have been met, we can now simply clone a repo locally and then push those changes to run on the server automatically
- Copy a git repo clone link
- Clone the repo locally on your machine
- Run npm init and then create the index.js file from before
- Install express
- Also add a .gitignore file to ignore meta data like node_modules and .DS_Store

``` bash
npm init

vim index.js

... add index code here

npm install express --save

vim .gitignore

... add meta data here

git add .
git commit -m "..."
git push origin

```

- Now simply clone the same repo on the EC2 server
- Use pm2 ls and pm2 delete "process-name" to kill any running processes
- Install PM2 on your local machine: npm i -g pm2
- Set up the ecosystem.config.js file
- ecosystem.config.js

``` JavaScript
module.exports = {
  apps: [{
    name: 'name-of-web-app',
    script: './index.js'
  }],
  deploy: {
    production: {
      user: 'ubuntu',
      host: 'name-of-amazon-host-ip.amazonaws.com',
      key: '~/.ssh/name-of-key-pair.pem',
      ref: 'origin/master',
      repo: 'git@github.com:name-of-user/name-of-repo.git',
      path: '/home/ubuntu/name-of-node-server-files-directory',
      'post-deploy': 'npm install && pm2 startOrRestart ecosystem.config.js'
    }
  }
}
```

- Now simply deploy setup using pm2 and *always* remember to commit and push git changes

``` bash
pm2 deploy ecosystem.config.js production setup

git add .
git commit -m "set up pm2"
git push origin

pm2 deploy ecosystem.config.js production

```

- Error will pop up due to bashrc file, log into EC2 server and modify .bashrc
- Cut and paste this code ABOVE the case $- in code

``` bash
# Should copy, then delete it from the bottom of .bashrc
export NVM_DIR="/home/ubuntu/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && . "$NVM_DIR/nvm.sh"  # This loads nvm

# Should then be pasted above this code
# If not running interactively, don't do anything
case $- in
  *i*) ;;
  *) return;;
esac
```

- On local machine run: pm2 deploy ecosystem.config.js production
- On local machine change the package.json file to include deploy and restart

``` JSON
"main": "index.js",
  "scripts": {
    "restart": "pm2 startOrRestart ecosystem.config.js",
    "deploy": "pm2 deploy ecosystem.config.js production",
    "test": "echo \"Error: no test specified\" && exit 1"
  },
```

- Also save pm2 locally using: npm i pm2 --save-dev
- Commit, push changes and then run: npm run-script deploy
- SSH into server and run to allow app restarts when server restarts: pm2 save

To Server HTML Files
- In the index.js file, we can setup a static directory that express will use to serve static files

``` JavaScript
const express = require('express')
const app = express()

app.use(express.static('name-of-folder'))
app.listen(3000, () => console.log('Server running on port 3000'))
```

- Now simply add html files in the static folder
- Commit, push changes and then run: node.js
- Visit it locally using: localhost:3000
- Deploy changes: npm run-script deploy

Quick Notes

- Remember to update AWS URL!! when restarting the server 
  - update in ecosystem.config.js

ssh -i tutorial.pem ubuntu@ec2-34-226-217-92.compute-1.amazonaws.com

- Commit your changes before deploying or error will occur!!

npm run-script deploy
