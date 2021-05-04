var tableTransaksi = {
  create: function () {
    // jika table tersebut datatable, maka clear and dostroy
    if ($.fn.DataTable.isDataTable('#tableTransaksi')) {
      //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
      $('#tableTransaksi').DataTable().clear();
      $('#tableTransaksi').DataTable().destroy();
    }

    $.ajax({
      url: '/api/transaksi',
      method: 'get',
      contentType: 'application/json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#tableTransaksi').DataTable({
            data: res,
            columns: [
              {
                title: "Tangal",
                data: "tanggalTransaksi"
              },
              {
                title: "No. Resi",
                data: "resi"
              },
              {
                title: "User Name",
                data: "firstName"
              },
              {
                title: "Barang",
                data: "namaBarang"
              },
              {
                title: "Jumlah",
                data: "jumlahBarang"
              },
              {
                title: "Total Berat (gram)",
                data: "beratBarang"
              },
              {
                title: "Pengirim",
                data: "namaPengirim"
              },
              {
                title: "Provinsi Pengirim",
                data: "provinceName"
              },
              {
                title: "Kota Pengirim",
                data: "cityName"
              },
              {
                title: "Alamat Pengirim",
                data: "alamatPengirim"
              },
              {
                title: "Telp. Pengirim",
                data: "telpPengirim"
              },
              {
                title: "Kode Pos Pengirim",
                data: "kodePosPengirim"
              },
              {
                title: "Penerima",
                data: "namaPenerima"
              },
              {
                title: "Provinsi Penerima",
                data: "provinceNamePenerima"
              },
              {
                title: "Kota Penerima",
                data: "cityNamePenerima"
              },
              {
                title: "Alamat Penerima",
                data: "alamatPenerima"
              },
              {
                title: "Telp. Penerima",
                data: "telpPenerima"
              },
              {
                title: "Kode Pos Penerima",
                data: "kodePosPenerima"
              },
              {
                title: "Layanan",
                data: "kategoriLayanan"
              },
              {
                title: "Ongkir",
                data: "ongkosKirim"
              },
              {
                title: "Status",
                data: "statusDelivery"
              },
              {
                title: "Penerima Paket",
                data: "penerimaPaket"
              },
              {
                title: "Foto Penerima",
                data: "fotoPenerima"
              },
              {
                title: "Action",
                data: null,
                render: function (data, type, row) {
                  return "<button class='btn-success edit' data-toggle='tooltip' title='edit' data-placement='bottom' onclick=formTransaksi.setEditData('" + data.idTransaksi + "') style='border-radius: 20%; margin: 8px 0 0 15px; height: 22px'><i class='fa fa-pencil-alt'></i></button>"
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

var dropdown = {

  pilihProvinsi: function () {
    $.ajax({
      type: "GET",
      url: "/api/provinsi",
      contentType: 'application/json',
      dataType: 'json',
      success: function (data) {
        var s = '<option value="-1">Pilih Provinsi</option>';
        for (var i = 0; i < data.length; i++) {
          s += '<option value="' + data[i].province_id + '">' + data[i].province + '</option>';
        }
        $("#province").html(s);
      }
    });
  },

  pilihProvinsiPenerima: function () {
    $.ajax({
      type: "GET",
      url: "/api/provinsi",
      contentType: 'application/json',
      dataType: 'json',
      success: function (data) {
        var s = '<option value="-1">Pilih Provinsi</option>';
        for (var i = 0; i < data.length; i++) {
          s += '<option value="' + data[i].province_id + '">' + data[i].province + '</option>';
        }
        $("#provincepenerima").html(s);
      }
    });
  },

  pilihLayanan: function (asal, tujuan, berat) {
    $.ajax({
      type: "GET",
      url: "/api/cost/" + asal + "/" + tujuan + "/" + berat,
      contentType: 'application/json',
      dataType: 'json',
      success: function (data) {
        var s = '<option value="-1">Pilih Layanan</option>';
        for (var i = 0; i < data.length; i++) {
          s += '<option value="' + data[i].cost[0].value + '">' + data[i].service + '</option>';
        }
        $("#layanan").html(s);
      }
    });
  }
}

var formTransaksi = {
  resetForm: function () {
    $('#formTransaksi')[0].reset();
    $('#idTransaksi').val("");
    $('#thumbnail').removeAttr('src')
  },

  saveForm : function (event) {
    event.preventDefault();

    // Get form
    var form = $('#formTransaksi')[0];

    // Create an FormData object
    var data = new FormData(form);

    // If you want to add an extra field for the FormData
    data.append('transaksi', new Blob([JSON.stringify({
      "idTransaksi": document.getElementById("idTransaksi").value,
      "email" : document.getElementById("email").value,
      "idPengirim" : document.getElementById("idPengirim").value,
      "namaPengirim" : document.getElementById("namaPengirim").value,
      "cityName" : document.getElementById("cityName").value,
      "provinceName" : document.getElementById("provinceName").value,
      "alamatPenerima" : document.getElementById("alamatPenerima").value,
      "kodePosPenerima" : document.getElementById("kodePosPenerima").value,
      "telpPenerima" : document.getElementById("telpPenerima").value,
      "idPenerima" : document.getElementById("idPenerima").value,
      "namaPenerima" : document.getElementById("namaPenerima").value,
      "cityNamePenerima" : document.getElementById("cityNamePenerima").value,
      "provinceNamePenerima" : document.getElementById("provinceNamePenerima").value,
      "alamatPengirim" : document.getElementById("alamatPengirim").value,
      "kodePosPengirim" : document.getElementById("kodePosPengirim").value,
      "telpPengirim" : document.getElementById("telpPengirim").value,
      "namaBarang" : document.getElementById("namaBarang").value,
      "jumlahBarang" : document.getElementById("jumlahBarang").value,
      "beratBarang" : document.getElementById("kategoriBeratBarang").value,
      "kategoriLayanan" : document.getElementById("kategoriLayanan").value,
      "ongkosKirim" : document.getElementById("ongkosKirim").value,







      // alamatPenerima: "80090"
      // alamatPengirim: "jakarta"
      // beratBarang: 8000
      // cityName: "Cilegon (Kota)"
      // cityNamePenerima: "Gunung Kidul"
      // cityPenerimaId: null
      // cityPengirimId: null
      // email: "adisugiarto270@gmail.com"
      // firstName: "sugi"
                                                    //fotoPenerima: null
      // idPenerima: 52
      // idPengirim: 52
      // idTransaksi: 52
      // idUser: 1
      // jumlahBarang: 8
      // kategoriLayanan: "OKE"
      // kodePosPenerima: "989890"
      // kodePosPengirim: "689"
      // lastName: "arto"
      // namaBarang: "678687"
      // namaPenerima: "jklkjlk"
      // namaPengirim: "adi"
      // ongkosKirim: 160000
      // password: "$2a$10$p.tqtgh297lCibUC.O//qusBnVrT2lU2CPCaggwM3hYNaA15nwh6i"
      // penerimaPaket: null
      // phone: "087828"
      // provinceIdPenerima: null
      // provinceName: "Banten"
      // provinceNamePenerima: "DI Yogyakarta"
      // province_id: null
      // resi: "YFA87273427"
      statusDelivery: "undelivered"
      // tanggalTransaksi: "2021-05-04T00:47:34.771+00:00"
      // telpPenerima: "90809"
      // telpPengirim: "087"
    })], {
      type: "application/json"
    }));

    // disabled the submit button
    $("#btn-save-transaksi").prop("disabled", true);

    $.ajax({
      type: "POST",
      enctype: 'multipart/form-data',
      url: "/api/transaksi",
      data: data,
      processData: false,
      contentType: false,
      cache: false,
      timeout: 600000,
      success: function (data) {

        // $("#result").text(data);
        console.log("SUCCESS : ", data);
        $("#btn-save-transaksi").prop("disabled", false);
        tableTransaksi.create();
        $('#modal-transaksi').modal('hide')
        console.log($('#fileImage').val())

      },
      error: function (e) {

        $("#result").text(e.responseText);
        console.log("ERROR : ", e);
        $("#btn-save-transaksi").prop("disabled", false);

      }
    });
  },

  setEditData: function (id) {

    $.ajax({
      url: '/api/transaksi/' + id,
      method: 'get',
      contentType: 'application/json',
      dataType: 'json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#formTransaksi').fromJSON(JSON.stringify(res));
          $('#modal-transaksi').modal('show')
        } else {
        }
      },
      erorrr: function (err) {
        console.log(err);
      }
    });
  }
};


