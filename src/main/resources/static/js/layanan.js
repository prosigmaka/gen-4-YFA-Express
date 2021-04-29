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
                    "<button class='btn-danger' data-toggle='tooltip' title='delete' data-placement='bottom' onclick=actionDelete.deleteConfirm('" + data.idLayanan + "') style='border-radius: 20%'><i class='fa fa-minus-circle'></i></button>"
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

var actionDelete = {
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
    if ($('#form-layanan').parsley().validate()) {
      var dataResult = getJsonForm($("#form-layanan").serializeArray(), true);

      $.ajax({
        url: '/api/layanan/' + dataResult.idLayanan,
        method: 'delete',
        success: function () {
          tableLayanan.create();
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
      url: '/api/layanan',
      method: 'delete',
      success: function () {
        tableLayanan.create()
        $('#modal-delete').modal('hide')
      }

    })
  }
};
