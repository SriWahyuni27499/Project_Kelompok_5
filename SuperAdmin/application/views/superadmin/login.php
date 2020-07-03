<body class="bg-gradient-primary">

  <div class="container"><br><br><br><br><br>

    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-5 col-lg-6 col-md-5">

        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            
            <div class="row">
              <div class="col-lg-12">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4">Masuk</h1>
                    <?php echo $this->session->flashdata('pesan')?>
                  </div>
                  <form method="post" action="<?php echo base_url ('superadmin/auth/proses_login')?>" class="user">
                    <div class="form-group">
                      <input type="text" class="form-control form-control-user" id="" placeholder="Username Anda" name="username">
                      <?php echo form_error('username', '<div class="text-danger small ml-3">','</div>')?>
                    </div>
                    <div class="form-group">
                      <input type="password" class="form-control form-control-user" id="exampleInputPassword" placeholder="Password Anda" name="password">
                      <?php echo form_error('password', '<div class="text-danger small ml-3">','</div>')?>
                    </div>
                    
                    <button class="btn btn-primary btn-user btn-block">Masuk</button>
                  
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>

    </div>

  </div>