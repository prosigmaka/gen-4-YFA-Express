var tableBeratBarang = {
  create: function () {
    // jika table tersebut datatable, maka clear and dostroy
    if ($.fn.DataTable.isDataTable('#tableBeratBarang')) {
      //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
      $('#tableBeratBarang').DataTable().clear();
      $('#tableBeratBarang').DataTable().destroy();
    }

    $.ajax({
      url: '/api/berat-barang',
      method: 'get',
      contentType: 'application/json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#tableBeratBarang').DataTable({
            data: res,
            columns: [
              {
                title: "Nama",
                data: "nama"
              },
              {
                title: "No. Telp",
                data: "telp"
              },
              {
                title: "Email",
                data: "email"
              },
              {
                title: "Action",
                data: null,
                render: function (data, type, row) {
                  return "<button class='btn-success' data-toggle='tooltip' title='edit' data-placement='bottom' onclick=formberatBarang.setEditData('" + data.idberatBarang + "') style='border-radius: 20%'><i class='fa fa-pencil-alt'></i></button>" + "<span>   </span>" +
                    "<button class='btn-danger' data-toggle='tooltip' title='delete' data-placement='bottom' onclick=actionDelete.deleteConfirm('" + data.idberatBarang + "') style='border-radius: 20%'><i class='fa fa-minus-circle'></i></button>"
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

var formberatBarang = {
  resetForm: function () {
    $('#form-berat-barang')[0].reset();
    $('#idberatBarang').val("");
  },
  saveForm: function () {
    if ($('#form-berat-barang').parsley().validate()) {
      var dataResult = getJsonForm($("#form-berat-barang").serializeArray(), true);
      $.ajax({
        url: '/api/berat-barang',
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
  setEditData: function (idCabang) {
    formberatBarang.resetForm();

    $.ajax({
      url: '/api/berat-barang/' + idCabang,
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
      url: '/api/berat-barang/' + id,
      method: 'get',
      contentType: 'application/json',
      dataType: 'json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#form-berat-barang').fromJSON(JSON.stringify(res));
          var h4 =  '<h4>Yakin ingin menghapus data ini?</h4>'
          var p = '<p>Data ini akan hilang setelah mengklik tombol "Delete"</p>';
          $('#modal-header').html(h4)
          $('#modal-hidden-id').html(p);
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
        url: '/api/berat-barang/' + dataResult.idberatBarang,
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
      url: '/api/berat-barang',
      method: 'delete',
      success: function () {
        tableBeratBarang.create()
        $('#modal-delete').modal('hide')
      }

    })
  }


};
