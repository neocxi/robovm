package org.bouncycastle.operator;

import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
// BEGIN android-removed
// import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
// END android-removed
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSASSAPSSparams;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;

public class DefaultDigestAlgorithmIdentifierFinder
    implements DigestAlgorithmIdentifierFinder
{
    private static Map digestOids = new HashMap();
    private static Map digestNameToOids = new HashMap();

    static
    {
        //
        // digests
        //
        // BEGIN android-removed
        // digestOids.put(OIWObjectIdentifiers.md4WithRSAEncryption, PKCSObjectIdentifiers.md4);
        // digestOids.put(OIWObjectIdentifiers.md4WithRSA, PKCSObjectIdentifiers.md4);
        // END android-removed
        digestOids.put(OIWObjectIdentifiers.sha1WithRSA, OIWObjectIdentifiers.idSHA1);

        // BEGIN android-removed
        // digestOids.put(PKCSObjectIdentifiers.sha224WithRSAEncryption, NISTObjectIdentifiers.id_sha224);
        // END android-removed
        digestOids.put(PKCSObjectIdentifiers.sha256WithRSAEncryption, NISTObjectIdentifiers.id_sha256);
        digestOids.put(PKCSObjectIdentifiers.sha384WithRSAEncryption, NISTObjectIdentifiers.id_sha384);
        digestOids.put(PKCSObjectIdentifiers.sha512WithRSAEncryption, NISTObjectIdentifiers.id_sha512);
        // BEGIN android-removed
        // digestOids.put(PKCSObjectIdentifiers.md2WithRSAEncryption, PKCSObjectIdentifiers.md2);
        // digestOids.put(PKCSObjectIdentifiers.md4WithRSAEncryption, PKCSObjectIdentifiers.md4);
        // END android-removed
        digestOids.put(PKCSObjectIdentifiers.md5WithRSAEncryption, PKCSObjectIdentifiers.md5);
        digestOids.put(PKCSObjectIdentifiers.sha1WithRSAEncryption, OIWObjectIdentifiers.idSHA1);

        digestOids.put(X9ObjectIdentifiers.ecdsa_with_SHA1, OIWObjectIdentifiers.idSHA1);
        // BEGIN android-removed
        // digestOids.put(X9ObjectIdentifiers.ecdsa_with_SHA224, NISTObjectIdentifiers.id_sha224);
        // END android-removed
        digestOids.put(X9ObjectIdentifiers.ecdsa_with_SHA256, NISTObjectIdentifiers.id_sha256);
        digestOids.put(X9ObjectIdentifiers.ecdsa_with_SHA384, NISTObjectIdentifiers.id_sha384);
        digestOids.put(X9ObjectIdentifiers.ecdsa_with_SHA512, NISTObjectIdentifiers.id_sha512);
        digestOids.put(X9ObjectIdentifiers.id_dsa_with_sha1, OIWObjectIdentifiers.idSHA1);

        // BEGIN android-removed
        // digestOids.put(NISTObjectIdentifiers.dsa_with_sha224, NISTObjectIdentifiers.id_sha224);
        // END android-removed
        digestOids.put(NISTObjectIdentifiers.dsa_with_sha256, NISTObjectIdentifiers.id_sha256);
        digestOids.put(NISTObjectIdentifiers.dsa_with_sha384, NISTObjectIdentifiers.id_sha384);
        digestOids.put(NISTObjectIdentifiers.dsa_with_sha512, NISTObjectIdentifiers.id_sha512);

        // BEGIN android-removed
        // digestOids.put(TeleTrusTObjectIdentifiers.rsaSignatureWithripemd128, TeleTrusTObjectIdentifiers.ripemd128);
        // digestOids.put(TeleTrusTObjectIdentifiers.rsaSignatureWithripemd160, TeleTrusTObjectIdentifiers.ripemd160);
        // digestOids.put(TeleTrusTObjectIdentifiers.rsaSignatureWithripemd256, TeleTrusTObjectIdentifiers.ripemd256);
        //
        // digestOids.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94, CryptoProObjectIdentifiers.gostR3411);
        // digestOids.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001, CryptoProObjectIdentifiers.gostR3411);
        // END android-removed

        digestNameToOids.put("SHA-1", OIWObjectIdentifiers.idSHA1);
        // BEGIN android-removed
        // digestNameToOids.put("SHA-224", NISTObjectIdentifiers.id_sha224);
        // END android-removed
        digestNameToOids.put("SHA-256", NISTObjectIdentifiers.id_sha256);
        digestNameToOids.put("SHA-384", NISTObjectIdentifiers.id_sha384);
        digestNameToOids.put("SHA-512", NISTObjectIdentifiers.id_sha512);

        // BEGIN android-removed
        // digestNameToOids.put("GOST3411", CryptoProObjectIdentifiers.gostR3411);
        //
        // digestNameToOids.put("MD2", PKCSObjectIdentifiers.md2);
        // digestNameToOids.put("MD4", PKCSObjectIdentifiers.md4);
        // END android-removed
        digestNameToOids.put("MD5", PKCSObjectIdentifiers.md5);

        // BEGIN android-removed
        // digestNameToOids.put("RIPEMD128", TeleTrusTObjectIdentifiers.ripemd128);
        // digestNameToOids.put("RIPEMD160", TeleTrusTObjectIdentifiers.ripemd160);
        // digestNameToOids.put("RIPEMD256", TeleTrusTObjectIdentifiers.ripemd256);
        // END android-removed
    }

    public AlgorithmIdentifier find(AlgorithmIdentifier sigAlgId)
    {
        AlgorithmIdentifier digAlgId;

        if (sigAlgId.getAlgorithm().equals(PKCSObjectIdentifiers.id_RSASSA_PSS))
        {
            digAlgId = RSASSAPSSparams.getInstance(sigAlgId.getParameters()).getHashAlgorithm();
        }
        else
        {
            digAlgId = new AlgorithmIdentifier((ASN1ObjectIdentifier)digestOids.get(sigAlgId.getAlgorithm()), DERNull.INSTANCE);
        }

        return digAlgId;
    }

    public AlgorithmIdentifier find(String digAlgName)
    {
        return new AlgorithmIdentifier((ASN1ObjectIdentifier)digestNameToOids.get(digAlgName), DERNull.INSTANCE);
    }
}