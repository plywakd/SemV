using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FPPCameraMovement : MonoBehaviour
{
    float vRotation = 0f;
    public float mouseSpeed = 250f;

    public Transform player;
    // Start is called before the first frame update
    void Start()
    {
        Cursor.lockState = CursorLockMode.Locked;   
    }

    // Update is called once per frame
    void Update()
    {
        float mouseX = Input.GetAxis("Mouse X") * Time.deltaTime * mouseSpeed;
        float mouseY = Input.GetAxis("Mouse Y") * Time.deltaTime * mouseSpeed;

        player.Rotate(Vector3.up * mouseX);

        vRotation -= mouseY;
        vRotation = Mathf.Clamp(vRotation, -90f, 90f);
        transform.localRotation = Quaternion.Euler(vRotation, 0f, 0f);
    }
}
