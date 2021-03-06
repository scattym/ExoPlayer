/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.exoplayer.demo;

import com.google.android.exoplayer.drm.MediaDrmCallback;
import com.google.android.exoplayer.drm.StreamingDrmSessionManager;
import com.google.android.exoplayer.util.Util;

import android.annotation.TargetApi;
import android.media.MediaDrm.KeyRequest;
import android.media.MediaDrm.ProvisionRequest;
import android.text.TextUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Demo {@link StreamingDrmSessionManager} for smooth streaming test content.
 */
@TargetApi(18)
public class SmoothStreamingTestMediaDrmCallback implements MediaDrmCallback {

    //https://foxtelott.live.ott.irdeto.com/playready/rightsmanager.asmx?CrmId=foxtelott&AccountId=foxtelott&ContentId=SEVASSMBRO003&SubContentType=AC&SessionId=C34E1A932885A15A&Ticket=A281E318490B3327
    //http://foxtelott.live.ott.irdeto.com/playready/rightsmanager.asmx?CrmId=foxtelott&AccountId=foxtelott&ContentId=SEVASSMBRO003&SubContentType=AC&SessionId=C34E1A932885A15A&Ticket=A281E318490B3327
//    private static final String PLAYREADY_TEST_DEFAULT_URI = "http://playready.directtaps.net/pr/svc/rightsmanager.asmx";
    private static final String PLAYREADY_TEST_DEFAULT_URI = "https://foxtelott.live.ott.irdeto.com/playready/rightsmanager.asmx";

  private static final Map<String, String> KEY_REQUEST_PROPERTIES;
    static {
    HashMap<String, String> keyRequestProperties = new HashMap<>();
    keyRequestProperties.put("Content-Type", "text/xml");
    keyRequestProperties.put("SOAPAction", "http://schemas.microsoft.com/DRM/2007/03/protocols/AcquireLicense");
    KEY_REQUEST_PROPERTIES = keyRequestProperties;
  }

  @Override
  public byte[] executeProvisionRequest(UUID uuid, ProvisionRequest request) throws IOException {
    String url = request.getDefaultUrl() + "&signedRequest=" + new String(request.getData());
    return Util.executePost(url, null, null);
      //return new byte[0];
  }

  @Override
  public byte[] executeKeyRequest(UUID uuid, KeyRequest request) throws Exception {
    String url = request.getDefaultUrl();
    url = "http://foxtelott.live.ott.irdeto.com/playready/rightsmanager.asmx?CrmId=foxtelott&AccountId=foxtelott&ContentId=SEVASSMBRO003&SubContentType=AC&SessionId=C34E1A932885A15A&Ticket=A281E318490B3327";
    url = "https://foxtelott.live.ott.irdeto.com/playready/rightsmanager.asmx?CrmId=foxtelott&AccountId=foxtelott&ContentId=SEVASSMBRO003&SubContentType=AC&SessionId=C34E1A932885A15A&Ticket=A281E318490B3327";
    url = "https://foxtelott.live.ott.irdeto.com/playready/rightsmanager.asmx?CrmId=foxtelott&AccountId=foxtelott&ContentId=SS_513513&SubContentType=AC&SessionId=054685AE10BB408C&Ticket=8E3FDFACD8414EBD";

    url = "http://sftl2.stage.ott.irdeto.com/playready/rightsmanager.asmx?CrmId=foxtelott&AccountId=foxtelott&ContentId=FXTL_ON257714&SubContentType=AC&SessionId=38F554AE89897147&Ticket=C64420BC7DED4E71";


    if (TextUtils.isEmpty(url)) {
      url = PLAYREADY_TEST_DEFAULT_URI + "?CrmId=foxtelott&AccountId=foxtelott&ContentId=SS_513513&SubContentType=AC&SessionId=054685AE10BB408C&Ticket=8E3FDFACD8414EBD";
    }
    return Util.executePost(url, request.getData(), KEY_REQUEST_PROPERTIES);
  }

}
