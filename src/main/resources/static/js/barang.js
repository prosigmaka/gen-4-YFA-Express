var tableBarang = {
  create: function () {
    // jika table tersebut datatable, maka clear and dostroy
    if ($.fn.DataTable.isDataTable('#tableBarang')) {
      //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
      $('#tableBarang').DataTable().clear();
      $('#tableBarang').DataTable().destroy();
    }

    $.ajax({
      url: '/api/barang',
      method: 'get',
      contentType: 'application/json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#tableBarang').DataTable({
            data: res,
            columns: [
              // {title: "Id Barang", data: "idBrang"},
              {title: "Nama ", data: "namaBarang"},
              {title: "jumlah", data: "jumlahBarang"},
              // {title: "Id Berat Barang", data: "idBeratBarang"},
              // {title: "Kategori Berat Barang", data: "kategoriBeratBarang"},
              {title: "Biaya Berat Barang", data: "biayaKategori"},
              // {title: "Id Layanan", data: "idLayanan"},
              // {title: "Kategori Layanan", data: "kategoriLayanan"},
              {title: "Biaya Layanan", data: "biayaLayanan"},
              // {title: "Id Pengirim", data: "idPengirim"},
              {title: "Nama Pengirim", data: "namaPengirim"},
              // {title: "Telp Pengirim", data: "telpPengirim"},
              {title: "Kota Pengirim", data: "kotaPengirim"},
              // {title: "Alamat Pengirim", data: "alamatPengirim"},
              // {title: "Kode Pos Pengirim", data: "kodePosPengirim"},
              // {title: "Id Penerima", data: "idPenerima"},
              {title: "Nama Penerima", data: "namaPenerima"},
              {title: "Telp Penerima", data: "telpPenerima"},
              {title: "Kota Penerima", data: "kotaPenerima"},
              {title: "Alamat Penerima", data: "alamatPenerima"},
              {title: "Kode Pos Penerima", data: "kodePosPenerima"},

              {
                title: "Action",
                data: null,
                render: function (data, type, row) {
                  return "<button class='btn-success' onclick=formBarang.setEditData('" + data.id + "') style='border-radius: 20%'><i class='fa fa-pencil-alt'></i></button>"+"<span></span>"+
                    "<button class='btn-danger' onclick=actionDelete.deleteConfirm('" + data.id + "') style='border-radius: 20%'><i class='fa fa-trash'></i></button>"

                }
              }
            ]
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

var formBarang = {
  resetForm: function () {
    $('#form-Barang')[0].reset();
    $("#id").val("");
    $("#idPengirim").val("");
    $("#idPenerima").val("");
  },
  saveForm: function () {
    if ($('#form-Barang').parsley().validate()) {
      var dataResult = getJsonForm($("#form-Barang").serializeArray(), true);

      $.ajax({
        url: '/api/barang',
        method: 'post',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(dataResult),
        success: function (res, status, xhr) {
          if (xhr.status == 200 || xhr.status == 201) {
            tableBarang.create();
            $('#modal-barang').modal('hide')

          } else {

          }
        },
        erorrr: function (err) {
          console.log(err);
        }
      });
    }
  }, setEditData: function (id) {
    formBarang.resetForm();

    $.ajax({
      url: '/api/barang/' + id,
      method: 'get',
      contentType: 'application/json',
      dataType: 'json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#form-Barang').fromJSON(JSON.stringify(res));
          $('#modal-barang').modal('show')

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
  deleteConfirm : function (id) {
    $.ajax({
      url: '/api/barang/'+ id,
      method: 'get',
      contentType: 'application/json',
      dataType: 'json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#form-Barang').fromJSON(JSON.stringify(res));
          $('#modal-delete').modal('show')
        } else {

        }
      },
      erorrr: function (err) {
        console.log(err);
      }
    });
  },
  deleteRowData: function () {
    if ($('#form-barang').parsley().validate()) {
      var dataResult = getJsonForm($("#form-barang").serializeArray(), true);

      $.ajax({
        url: '/api/barang/' + dataResult.idBarang,
        method: 'delete',
        success: function () {
          tableBarang.create();
          $('#modal-delete').modal('hide')
        },
        erorrr: function (err) {
          console.log(err);
        }
      });
    }
  }
}
