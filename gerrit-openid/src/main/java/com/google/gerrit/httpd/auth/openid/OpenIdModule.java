// Copyright (C) 2009 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.gerrit.httpd.auth.openid;

import com.google.gerrit.httpd.rpc.RpcServletModule;
import com.google.inject.servlet.ServletModule;

/** Servlets and RPC support related to OpenID authentication. */
public class OpenIdModule extends ServletModule {
  @Override
  protected void configureServlets() {
    serve("/" + OpenIdServiceImpl.RETURN_URL).with(OpenIdLoginServlet.class);
    serve("/" + XrdsServlet.LOCATION).with(XrdsServlet.class);
    filter("/").through(XrdsFilter.class);

    install(new RpcServletModule(RpcServletModule.PREFIX) {
      @Override
      protected void configureServlets() {
        rpc(OpenIdServiceImpl.class);
      }
    });
  }
}
