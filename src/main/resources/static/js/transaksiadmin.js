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
                title: "Kurir",
                data: "namaKurir"
              },
              {
                title: "Penerima Paket",
                data: "penerimaPaket"
              },
              {
                title: "Foto",
                data: null,
                render: function (data, type, row) {
                  return "<img src='/api/transaksi/getFoto/"+data.idTransaksi+"' alt='myImage' style='border-radius: 5px; width: 100px'>"
                }
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
  },

  pilihKurir: function () {
    $.ajax({
      type: "GET",
      url: "/api/kurir",
      contentType: 'application/json',
      dataType: 'json',
      success: function (data) {
        var s = '<option value="-1">Pilih Kurir</option>';
        for (var i = 0; i < data.length; i++) {
          s += '<option value="' + data[i].idKurir + '">' + data[i].namaKurir + '</option>';
        }
        $("#kurir").html(s);
      }
    });
  },
}

var formTransaksi = {
  resetForm: function () {
    $('#formTransaksi')[0].reset();
    $('#idTransaksi').val("");
    $('#thumbnail').attr('src', '/img/basic.png')
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
      "idKurir" : document.getElementById("kurir").value,
      "penerimaPaket" : document.getElementById("penerimaPaket").value,
      "statusDelivery" : document.getElementById("statusDelivery").value,
      "fotoPenerima" : document.getElementById("fotoPenerima").value
    })], {
      type: "application/json"
    }));

    // disabled the submit button
    $("#btn-save-transaksi").prop("disabled", true);

    $.ajax({
      type: "POST",
      enctype: 'multipart/form-data',
      url: "/api/transaksi/admin",
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
        formTransaksi.resetForm();
        $('#modal-transaksi').modal('hide')
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
          $('#select2-province-container').text(res.provinceName)
          $('#select2-city_name-container').text(res.cityName)
          $('#select2-provincepenerima-container').text(res.provinceNamePenerima)
          $('#select2-city_namepenerima-container').text(res.cityNamePenerima)
          $('#status').val(res.statusDelivery)
          $('#siPenerimaPaket').val(res.penerimaPaket)
          $('#select2-kurir-container').text(res.namaKurir)
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


