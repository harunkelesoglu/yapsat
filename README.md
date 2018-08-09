<p align="center">
  <img src="https://www.imageupload.co.uk/images/2018/02/16/82785541-606c-4506-82ae-7fef916fa7b3.png" border="0">
</p>
<article class="markdown-body entry-content" itemprop="text"><h1><a href="#scrapy" aria-hidden="true" class="anchor" id="user-content-scrapy"></a>README YapSat</h1>
<p>This project was created on <b>"Semih Öztürk Hackathon"</b></p>

<h2><a href="#table-of-contents" aria-hidden="true" class="anchor" id="user-content-table-of-contents"></a>Table of Contents</h2>

<ul>
<li><a href="#what">What is YapSat ?</a></li>
<li><a href="#endpoints">Endpoints</a></li>
<li><a href="#installation">Installation</a></li>
<li><a href="#structure">Project Structure</a></li>
</ul>



<h2><a href="#what" aria-hidden="true" class="anchor" id="user-content-what"></a>What is YapSat ?</h2>
<p>Yapsat is a location-based platform which Housewifes makes money in return makes food. You can give an order home cooking As a customer to office meeting and organizations or you can give order home cooking as a student from the nearest point or specific selling point.	 Note: food was chosen as a startup type on this project. </p>
<h3>DEMO</h3>
<img src="../public/assets/gifs/demo.gif">
<h3>Features</h3>
<ul>
  <li>Recipients see the nearest seller which location close to the recipient and  then view after that commence the purchase.</li>
  <li>Sellers add their products with specifications and convenient location to sale after that commence selling</li>
</ul>

<h2><a href="#endpoinst" aria-hidden="true" class="anchor" id="user-content-what"></a>EndPoints</h2>
<h3>This app have the following endpoints</h3>
<ul>
<li><h4>GET - /api/user/</h4> Return all users on system.</li>
<li><h4>GET - /api/user/{username}</h4> Return user by username in path</li>
<li><h4>POST - /api/user/register </h4> Add new user.</li>
<li><h4>POST - /api/user/update </h4> Update user information.</li>
<li><h4>DELETE - /api/user/{username} </h4> Delet user by username in path</li>
</ul>


<h2><a href="#installation" aria-hidden="true" class="anchor" id="user-content-installation"></a>Project Dependencies Installation and Run</h2>
<p>You should run the following command to install depencies of project and run</p>

<div class="highlight highlight-source-shell">
<pre>$ mvn install 
     $ mvn clean package
     $ mvn spring-boot:run 
</pre>
</div>

<h2><a href="#structure" aria-hidden="true" class="anchor" id="user-content-installation"></a>Project Structure</h2>
<p>Project source structure</p>

<div class="highlight highlight-source-shell">
<pre> 
├── ArgedorApplication.java
├── DBSeeder.java
├── config  
    └── ApplicationSecurity.java
    └── Documentation.java        
├── controler      
    └── UserController.java
├── model    
    └── User.java
    └── Order.java
    └── Product.java
    └── Role.java
├── repository   
    └── OrderRepository.java
    └── ProductRepository.java
    └── RoleRepository.java
    └── UserRepository.java
├── exception
    └── ConflictException.java
    └── UserNotFoundException.java
├── security
    └── JWTAuthenticationFİlter.java
    └── JWTAuthorization.java
    └── SecurityConstants.java         
</pre>
</div>

</article>
