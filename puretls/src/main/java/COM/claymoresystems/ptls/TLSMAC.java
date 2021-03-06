/**
   TLSMAC.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Tue May 18 17:56:37 1999

   This package is a SSLv3/TLS implementation written by Eric Rescorla
   <ekr@rtfm.com> and licensed by Claymore Systems, Inc.

   Redistribution and use in source and binary forms, with or without
   modification, are permitted provided that the following conditions
   are met:
   1. Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
   2. Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
   3. All advertising materials mentioning features or use of this software
      must display the following acknowledgement:
      This product includes software developed by Claymore Systems, Inc.
   4. Neither the name of Claymore Systems, Inc. nor the name of Eric
      Rescorla may be used to endorse or promote products derived from this
      software without specific prior written permission.

   THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND
   ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
   IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
   ARE DISCLAIMED.  IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE
   FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
   DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
   OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
   HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
   LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
   OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
   SUCH DAMAGE.

   $Id: TLSMAC.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.ptls;
import COM.claymoresystems.util.Util;
import COM.claymoresystems.util.Bench;
import java.security.MessageDigest;
import xjava.security.Parameterized;


class TLSMAC extends SSLMAC{
     public static byte[] calcMAC(SSLCipherState st,
       int ct,int version,long sequence,byte[] buf){
       MessageDigest md;
       byte[] tmp;
	 
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"MAC Key",st.mac_key);



       try {
	 md=MessageDigest.getInstance("HMAC-" +
	   st.cipher_suite.getDigestAlg());
	 ((Parameterized)md).setParameter("key",st.mac_key);
       } catch (java.lang.Exception e){
	 throw new InternalError(e.toString());
       }

       tmp=Util.toBytes(sequence);
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Sequence",tmp);
       md.update(tmp);

       byte[] ct_b={(byte)ct};
       md.update(ct_b);
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Content type",ct_b);

       tmp=Util.toBytes((long)version,2);
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Version",tmp);
       md.update(tmp);
       
       tmp=Util.toBytes((long)buf.length,2);
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Length",tmp);
       md.update(tmp);

       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Data",buf);
       md.update(buf);

       tmp=md.digest();
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"Computed TLS MAC",tmp);

       return tmp;
     }
}
       

       
     


