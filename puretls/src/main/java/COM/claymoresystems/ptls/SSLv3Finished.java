/**
   SSLv3Finished.java

   Copyright (C) 1999, Claymore Systems, Inc.
   All Rights Reserved.

   ekr@rtfm.com  Tue May 11 11:18:23 1999

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

   $Id: SSLv3Finished.java,v 1.1.1.1 2003/05/09 20:36:14 gawor Exp $

*/

package COM.claymoresystems.ptls;
import java.security.MessageDigest;

// As kind of a layer violation, this computes the finished
// hashes. We need to do it here and not at the handshake
// level because the computation differs for v3 and TLS
class SSLv3Finished 
{
     static byte[] cli={0x43,0x4c,0x4e,0x54};
     static byte[] ser={0x53,0x52,0x56,0x52};
     
     static byte[] computeFinished(SSLHandshake hs,boolean mine){
       SSLHandshakeHashes hashes=mine?hs.hashes:hs.save_hashes;
       MessageDigest md5=hashes.getMD5Digest();
       MessageDigest sha=hashes.getSHADigest();
       int i;
       byte[] tmp;
       byte[] output=new byte[36];
       
       byte[] Sender=(hs.client ^ mine)?ser:cli;

       // md5_hash
       md5.update(Sender);
       md5.update(hs.master_secret);
       for(i=0;i<48;i++){
	 md5.update(SSLHandshake.pad_1);
       }
       tmp=md5.digest();
       
       md5.update(hs.master_secret);
       for(i=0;i<48;i++){
	 md5.update(SSLHandshake.pad_2);
       }
       md5.update(tmp);
       tmp=md5.digest();
       System.arraycopy(tmp,0,output,0,tmp.length);
       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"MD5 handshake hash",
	 tmp);

       // sha_hash
       sha.update(Sender);
       sha.update(hs.master_secret);
       for(i=0;i<40;i++){
	 sha.update(SSLHandshake.pad_1);
       }
       tmp=sha.digest();
       
       sha.update(hs.master_secret);
       for(i=0;i<40;i++){
	 sha.update(SSLHandshake.pad_2);
       }
       sha.update(tmp);
       tmp=sha.digest();
       System.arraycopy(tmp,0,output,16,tmp.length);

       SSLDebug.debug(SSLDebug.DEBUG_CRYPTO,"SHA handshake hash",
	 tmp);
       return(output);
     }
}

       
