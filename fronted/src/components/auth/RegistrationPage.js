import React from 'react';

const LoginPage = () => {
  return (
<>
    <meta charSet="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0"
    crossOrigin="anonymous"
  />
  <link rel="stylesheet" href="styles.css" />
  <link rel="shortcut icon" href="favicon.png" type="image/png" />
  <title>Title</title>
  <style
    dangerouslySetInnerHTML={{
      __html:
        "\n  .caret {\n    display: inline-block;\n    width: 13px;\n    height: 13px;\n    background-color: lightgray;\n  }\n  .bg-custom-1 {\n    background-color: #85144b;\n  }\n  \n  .bg-custom-2 {\n  background-image: linear-gradient(15deg, #13547a 0%, #80d0c7 100%);\n  }\n\n  .boyut{\n    font-size: 19px;\n  }\n"
    }}
  />
  <nav
    className="navbar navbar-expand-lg navbar-light bg-light"
    style={{
      background:
        "radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(212,221,232,1) 100%)"
    }}
  >
    <div className="container">
      <a className="navbar-brand me-0 me-lg-5" href="#">
        <img
          src="img/lofoufak.png"
          alt=""
          className="rounded-circle d-inline-block align-text-top"
        />
      </a>
      <button
        className="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon" />
      </button>
      <div className="collapse navbar-collapse" id="navbarSupportedContent">
        <ul className="navbar-nav me-auto mb-2 mb-lg-0 boyut">
          <li className="nav-item">
            <a className="nav-link active" aria-current="page" href="#">
              Home
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#">
              Student Friendly Places
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#">
              Table D'hote Menu
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#">
              Bus Schedule
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#">
              Student Clubs
            </a>
          </li>
        </ul>
        <div className="form-check form-switch ms-auto mt-3 me-3">
          <label className="form-check-label ms-3" htmlFor="lightSwitch">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width={25}
              height={25}
              fill="currentColor"
              className="bi bi-brightness-high"
              viewBox="0 0 16 16"
            >
              <path d="M8 11a3 3 0 1 1 0-6 3 3 0 0 1 0 6zm0 1a4 4 0 1 0 0-8 4 4 0 0 0 0 8zM8 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 0zm0 13a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 13zm8-5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2a.5.5 0 0 1 .5.5zM3 8a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2A.5.5 0 0 1 3 8zm10.657-5.657a.5.5 0 0 1 0 .707l-1.414 1.415a.5.5 0 1 1-.707-.708l1.414-1.414a.5.5 0 0 1 .707 0zm-9.193 9.193a.5.5 0 0 1 0 .707L3.05 13.657a.5.5 0 0 1-.707-.707l1.414-1.414a.5.5 0 0 1 .707 0zm9.193 2.121a.5.5 0 0 1-.707 0l-1.414-1.414a.5.5 0 0 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .707zM4.464 4.465a.5.5 0 0 1-.707 0L2.343 3.05a.5.5 0 1 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .708z" />
            </svg>
          </label>
          <input
            className="form-check-input"
            type="checkbox"
            id="lightSwitch"
          />
        </div>
        <div className="dropdown" style={{ marginRight: 10 }}>
          <img
            className="rounded-circle dropdown-toggle"
            width={40}
            src="https://avatars.githubusercontent.com/u/72274639?v=4"
            id="dropdownMenuButton1"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          />
          <span
            className="caret"
            id="dropdownMenuButton1"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
            <img src="img/down.svg" width="200%" />{" "}
          </span>
          <ul className="dropdown-menu" aria-labelledby="dropdownMenuButton1">
            <li>
              <a className="dropdown-item" href="#">
                Profilim
              </a>
            </li>
            <li>
              <a className="dropdown-item" href="#">
                Mesajlarım
              </a>
            </li>
            <li>
              <a className="dropdown-item" href="#">
                İlanlarım
              </a>
            </li>
            <li>
              <hr className="dropdown-divider" />
            </li>
            <li>
              <a className="dropdown-item" href="#">
                Logout
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </nav>
  <main>
    <form method="POST">
      <div className="album bg-light mt-3">
        <div className="container">
          <div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <div className="col">
              <div className="card shadow-sm">
                <center>
                  <img
                    className="rounded-circle dropdown-toggle mt-3"
                    width={145}
                    src="https://placehold.co/125x125?text=Insert+Image"
                    id="dropdownMenuButton1"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  />
                  <br />
                  <button
                    type="button"
                    className="btn btn-m  btn-outline-secondary btn-block px-4 center"
                  >
                    Add Photo
                  </button>
                  <div className="card-body bg-light">
                    <div className="d-flex justify-content-between align-items-center"></div>
                  </div>
                </center>
              </div>
            </div>
            <div className="col-md-6">
              <div className="card shadow-sm ">
                <div className="card-body  bg-light w-100">
                  <div className="form-group mb-2">
                    <input
                      type="email"
                      className="form-control"
                      name="eposta"
                      id="eposta"
                      aria-describedby="helpId"
                      placeholder="Bilkent Mail"
                      required=""
                    />
                  </div>
                  <div className="form-group mb-2">
                    <input
                      type="password"
                      className="form-control"
                      name="parola"
                      id="parola"
                      aria-describedby="helpId"
                      placeholder="Password"
                      required=""
                    />
                  </div>
                  <div className="form-group mb-2">
                    <input
                      type="password"
                      className="form-control"
                      name="reparola"
                      id="reparola"
                      aria-describedby="helpId"
                      placeholder="Re-type Password"
                      required=""
                    />
                  </div>
                  <div className="form-group mb-2">
                    <input
                      type="text"
                      className="form-control"
                      name="ad"
                      id="ad"
                      aria-describedby="helpId"
                      placeholder="Name"
                      required=""
                    />
                  </div>
                  <div className="form-group mb-2">
                    <input
                      type="text"
                      className="form-control"
                      name="soyad"
                      id="soyad"
                      aria-describedby="helpId"
                      placeholder="Surname"
                      required=""
                    />
                  </div>
                  <div className="form-group mb-2">
                    <textarea
                      placeholder="Introduce Yourself"
                      className="form-control"
                      name="bio"
                      id="bio"
                      cols={30}
                      rows={5}
                      style={{ resize: "both" }}
                      defaultValue={""}
                    />
                  </div>
                  <div className="form-group">
                    <button
                      type="submit"
                      className="form-control btn btn-m  btn-outline-secondary btn-block px-4 center"
                      onclick="ansValidation(event)"
                    >
                      Register
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </form>
  </main>
</>
  );
};

export default LoginPage;