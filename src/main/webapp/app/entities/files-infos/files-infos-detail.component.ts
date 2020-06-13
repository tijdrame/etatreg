import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFilesInfos } from 'app/shared/model/files-infos.model';

@Component({
  selector: 'jhi-files-infos-detail',
  templateUrl: './files-infos-detail.component.html',
})
export class FilesInfosDetailComponent implements OnInit {
  filesInfos: IFilesInfos | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ filesInfos }) => (this.filesInfos = filesInfos));
  }

  previousState(): void {
    window.history.back();
  }
}
