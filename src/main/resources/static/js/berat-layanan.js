var tableBeratBarang = {
  create: function () {
    // jika table tersebut datatable, maka clear and dostroy
    if ($.fn.DataTable.isDataTable('#tableBeratBarang')) {
      //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
      $('#tableBeratBarang').DataTable().clear();
      $('#tableBeratBarang').DataTable().destroy();
    }

    $.ajax({
      url: '/api/beratBarang',
      method: 'get',
      contentType: 'application/json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#tableBeratBarang').DataTable({
            data: res,
            columns: [
              {
                title: "ID",
                data: "idBeratBarang"
              },
              {
                title: "Kategori",
                data: "kategoriBeratBarang"
              },
              {
                title: "Biaya",
                data: "biayaKategori"
              },
              {
                title: "Action",
                data: null,
                render: function (data, type, row) {
                  return "<button class='btn-success' data-toggle='tooltip' title='edit' data-placement='bottom' onclick=formBeratBarang.setEditData('" + data.idBeratBarang + "') style='border-radius: 20%; margin-right: 5px'><i class='fa fa-pencil-alt'></i></button>" +
                    "<button class='btn-danger' data-toggle='tooltip' title='delete' data-placement='bottom' onclick=actionDelete.deleteConfirm('" + data.idBeratBarang + "') style='border-radius: 20%'><i class='fa fa-minus-circle'></i></button>"
                }
              }
            ],
            scrollX : true
          });

        } else {

        }
      },
      error: function (err) {
        console.log(err);
      }
    });


  }
};

var formBeratBarang = {
  resetForm: function () {
    $('#form-berat-barang')[0].reset();
    $('#idBeratBarang').val("");
  },
  saveForm: function () {
    if ($('#form-berat-barang').parsley().validate()) {
      var dataResult = getJsonForm($("#form-berat-barang").serializeArray(), true);
      $.ajax({
        url: '/api/beratBarang',
        method: 'post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(dataResult),
        success: function (res, status, xhr) {
          if (xhr.status == 200 || xhr.status == 201) {
            tableBeratBarang.create();
            $('#modal-berat-barang').modal('hide')

          } else {

          }
        },
        erorrr: function (err) {
          console.log(err);
        }
      });
    }

  },
  setEditData: function (id) {
    formBeratBarang.resetForm();

    $.ajax({
      url: '/api/beratBarang/' + id,
      method: 'get',
      contentType: 'application/json',
      dataType: 'json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#form-berat-barang').fromJSON(JSON.stringify(res));
          $('#modal-berat-barang').modal('show')

        } else {

        }
      },
      erorrr: function (err) {
        console.log(err);
      }
    });
  }
};

var actionDelete = {
  deleteConfirm: function (id) {
    $.ajax({
      url: '/api/beratBarang/' + id,
      method: 'get',
      contentType: 'application/json',
      dataType: 'json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#form-berat-barang').fromJSON(JSON.stringify(res));
          var p =  '<p>Yakin ingin menghapus data ini?</p>'
          $('.modal-body-delete').html(p)
          $('#delete-row-bb').removeClass('delete-table')
          $('#modal-delete').modal('show')
        } else {

        }
      },
      erorrr: function (err) {
        console.log(err);
      }
    });
  },

  deleteRowData : function () {
    if ($('#form-berat-barang').parsley().validate()) {
      var dataResult = getJsonForm($("#form-berat-barang").serializeArray(), true);

      $.ajax({
        url: '/api/beratBarang/' + dataResult.idBeratBarang,
        method: 'delete',
        success: function () {
          tableBeratBarang.create();
          $('#modal-delete').modal('hide');
        },
        erorrr: function (err) {
          console.log(err);
        }
      });
    }
  },

  deleteTable : function () {
    $.ajax({
      url: '/api/beratBarang',
      method: 'delete',
      success: function () {
        tableBeratBarang.create()
        $('#modal-delete').modal('hide')
      }

    })
  }
};

//layanan
var tableLayanan = {
  create: function () {
    // jika table tersebut datatable, maka clear and dostroy
    if ($.fn.DataTable.isDataTable('#tableLayanan')) {
      //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
      $('#tableLayanan').DataTable().clear();
      $('#tableLayanan').DataTable().destroy();
    }

    $.ajax({
      url: '/api/layanan',
      method: 'get',
      contentType: 'application/json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#tableLayanan').DataTable({
            data: res,
            columns: [
              {
                title: "ID",
                data: "idLayanan"
              },
              {
                title: "Kategori",
                data: "kategoriLayanan"
              },
              {
                title: "Biaya",
                data: "biayaLayanan"
              },
              {
                title: "Action",
                data: null,
                render: function (data, type, row) {
                  return "<button class='btn-success' data-toggle='tooltip' title='edit' data-placement='bottom' onclick=formLayanan.setEditData('" + data.idLayanan + "') style='border-radius: 20%; margin-right: 5px'><i class='fa fa-pencil-alt'></i></button>" +
                    "<button class='btn-danger' data-toggle='tooltip' title='delete' data-placement='bottom' onclick=actionDeleteLayanan.deleteConfirm('" + data.idLayanan + "') style='border-radius: 20%'><i class='fa fa-minus-circle'></i></button>"
                }
              }
            ],
            scrollX : true
          });

        } else {

        }
      },
      error: function (err) {
        console.log(err);
      }
    });


  }
};

var formLayanan = {
  resetForm: function () {
    $('#form-layanan')[0].reset();
    $('#idLayanan').val("");
  },
  saveForm: function () {
    if ($('#form-layanan').parsley().validate()) {
      var dataResult = getJsonForm($("#form-layanan").serializeArray(), true);
      $.ajax({
        url: '/api/layanan',
        method: 'post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(dataResult),
        success: function (res, status, xhr) {
          if (xhr.status == 200 || xhr.status == 201) {
            tableLayanan.create();
            $('#modal-layanan').modal('hide')

          } else {

          }
        },
        erorrr: function (err) {
          console.log(err);
        }
      });
    }

  },
  setEditData: function (id) {
    formLayanan.resetForm();

    $.ajax({
      url: '/api/layanan/' + id,
      method: 'get',
      contentType: 'application/json',
      dataType: 'json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#form-layanan').fromJSON(JSON.stringify(res));
          $('#modal-layanan').modal('show')

        } else {

        }
      },
      erorrr: function (err) {
        console.log(err);
      }
    });
  }
};

var actionDeleteLayanan = {
  deleteConfirm: function (id) {
    $.ajax({
      url: '/api/layanan/' + id,
      method: 'get',
      contentType: 'application/json',
      dataType: 'json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#form-layanan').fromJSON(JSON.stringify(res));
          var p =  '<p>Yakin ingin menghapus data ini?</p>'
          $('.modal-body-delete').html(p)
          $('#delete-row-layanan').removeClass('delete-table-layanan')
          $('#modal-delete-layanan').modal('show')
        } else {

        }
      },
      erorrr: function (err) {
        console.log(err);
      }
    });
  },

  deleteRowData : function () {
    if ($('#form-layanan').parsley().validate()) {
      var dataResult = getJsonForm($("#form-layanan").serializeArray(), true);

      $.ajax({
        url: '/api/layanan/' + dataResult.idLayanan,
        method: 'delete',
        success: function () {
          tableLayanan.create();
          $('#modal-delete-layanan').modal('hide');
        },
        erorrr: function (err) {
          console.log(err);
        }
      });
    }
  },

  deleteTable : function () {
    $.ajax({
      url: '/api/layanan',
      method: 'delete',
      success: function () {
        tableLayanan.create()
        $('#modal-delete-layanan').modal('hide')
      }

    })
  }
};

//kota
var tableKota = {
  create: function () {
    // jika table tersebut datatable, maka clear and dostroy
    if ($.fn.DataTable.isDataTable('#tableKota')) {
      //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
      $('#tableKota').DataTable().clear();
      $('#tableKota').DataTable().destroy();
    }

    $.ajax({
      url: '/api/kota',
      method: 'get',
      contentType: 'application/json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#tableKota').DataTable({
            data: res,
            columns: [
              {
                title: "ID",
                data: "idKota"
              },
              {
                title: "Kota",
                data: "kota"
              },
              {
                title: "Biaya",
                data: "estimasiBiaya"
              },
              {
                title: "Action",
                data: null,
                render: function (data, type, row) {
                  return "<button class='btn-success' data-toggle='tooltip' title='edit' data-placement='bottom' onclick=formKota.setEditData('" + data.idKota + "') style='border-radius: 20%; margin-right: 5px'><i class='fa fa-pencil-alt'></i></button>" +
                    "<button class='btn-danger' data-toggle='tooltip' title='delete' data-placement='bottom' onclick=actionDeleteKota.deleteConfirm('" + data.idKota + "') style='border-radius: 20%'><i class='fa fa-minus-circle'></i></button>"
                }
              }
            ],
            scrollX : true
          });

        } else {

        }
      },
      error: function (err) {
        console.log(err);
      }
    });


  }
};

var formKota = {
  resetForm: function () {
    $('#form-kota')[0].reset();
    $('#idKota').val("");
  },
  saveForm: function () {
    if ($('#form-kota').parsley().validate()) {
      var dataResult = getJsonForm($("#form-kota").serializeArray(), true);
      $.ajax({
        url: '/api/kota',
        method: 'post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(dataResult),
        success: function (res, status, xhr) {
          if (xhr.status == 200 || xhr.status == 201) {
            tableKota.create();
            $('#modal-kota').modal('hide')

          } else {

          }
        },
        erorrr: function (err) {
          console.log(err);
        }
      });
    }

  },
  setEditData: function (id) {
    formKota.resetForm();

    $.ajax({
      url: '/api/kota/' + id,
      method: 'get',
      contentType: 'application/json',
      dataType: 'json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#form-kota').fromJSON(JSON.stringify(res));
          $('#modal-kota').modal('show')

        } else {

        }
      },
      erorrr: function (err) {
        console.log(err);
      }
    });
  }
};

var actionDeleteKota = {
  deleteConfirm: function (id) {
    $.ajax({
      url: '/api/kota/' + id,
      method: 'get',
      contentType: 'application/json',
      dataType: 'json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#form-kota').fromJSON(JSON.stringify(res));
          var p =  '<p>Yakin ingin menghapus data ini?</p>'
          $('.modal-body-delete').html(p)
          $('#delete-row-kota').removeClass('delete-table-kota')
          $('#modal-delete-kota').modal('show')
        } else {

        }
      },
      erorrr: function (err) {
        console.log(err);
      }
    });
  },

  deleteRowData : function () {
    if ($('#form-kota').parsley().validate()) {
      var dataResult = getJsonForm($("#form-kota").serializeArray(), true);

      $.ajax({
        url: '/api/kota/' + dataResult.idKota,
        method: 'delete',
        success: function () {
          tableKota.create();
          $('#modal-delete-kota').modal('hide');
        },
        erorrr: function (err) {
          console.log(err);
        }
      });
    }
  },

  deleteTable : function () {
    $.ajax({
      url: '/api/kota',
      method: 'delete',
      success: function () {
        tableKota.create()
        $('#modal-delete-kota').modal('hide');
      }

    })
  }
};

